package cn.charlesxu.LabManager.service.algorithm;

import cn.charlesxu.LabManager.entity.Lab;
import cn.charlesxu.LabManager.entity.Order;
import cn.charlesxu.LabManager.entity.OrderDetail;
import cn.charlesxu.LabManager.service.LabService;
import cn.charlesxu.LabManager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static cn.charlesxu.LabManager.entity.define.OrderDefine.unArranged;

/**
 * Created by
 * User: Charles Xu
 * Date: 6/2/2018
 * Time: 13:47
 */
@Component
public class ArrangeAlgorithm {

    @Autowired
    private OrderService orderService;

    @Autowired
    private LabService labService;


    public int getPeoCountArranged(List<Order> orderList, Integer labId, Integer orderWeek, Integer weekDays, Integer classNum) {
        int peoCount = 0;

        for (Order order : orderList) {
            //对于每个order 取其已经安排的orderDetail
            OrderDetail arrangedOrderDetail = null;
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                if (order.getPassFlag() == orderDetail.getType()) {
                    arrangedOrderDetail = orderDetail;
                    break;
                }
            }
            if (arrangedOrderDetail == null) {
                //return -5;
                continue;
            }

            for (Integer arrangedOrderWeek : arrangedOrderDetail.getOrderWeek()) {
                //找到对应的周次
                if (arrangedOrderWeek.intValue() == orderWeek.intValue()) {
                    //找到对应的星期
                    if (arrangedOrderDetail.getWeekDays().get(0).intValue() == weekDays.intValue()) {
                        //找到对应的节次
                        for (Integer arrangedClassNum : arrangedOrderDetail.getClassNum()) {
                            if (arrangedClassNum.intValue() == classNum.intValue()) {
                                //找到对应的实验室,加上人数
                                if (arrangedOrderDetail.getFirstLab().intValue() == labId.intValue()) {
                                    peoCount += arrangedOrderDetail.getFirstLabPeoCount().intValue();
                                } else if (arrangedOrderDetail.getSecondLab().intValue() == labId.intValue()) {
                                    peoCount += arrangedOrderDetail.getSecondLabPeoCount().intValue();
                                } else if (arrangedOrderDetail.getThirdLab().intValue() == labId.intValue()) {
                                    peoCount += arrangedOrderDetail.getThirdLabPeoCount().intValue();
                                }
                            }
                        }
                    }
                }
            }

        }

        return peoCount;
    }


    /**
     * 功能：检查实验室是否可以被安排
     *
     * @param labId     实验室ID
     * @param peoCount  拟安排人数
     * @param orderWeek 拟安排预约周次
     * @param weekDays  拟安排星期
     * @param classNum  拟安排节次
     * @param semester  拟安排学期
     * @return 是否可以安排，可以为1，否则为0
     * <p>
     * ERROR CODE :
     * -1:Wrong_labId;
     * -2:Wrong_orderWeek;
     * -3:Wrong_weekDays;
     * -4:Wrong_classNum;
     * -5:DataError(Have UnfinishedOrder);
     */
    public int checkLabAvailable(Integer labId, Integer peoCount, List<Integer> orderWeek, List<Integer> weekDays, List<Integer> classNum, String semester) {
        //获得labId下所有已经安排的预约单（即已经被占的空位）
        List<Order> labOrderList = orderService.getOrderByLabIdAndSemester(labId, semester);
        labOrderList = orderService.getFinishedOrderList(labOrderList);

        //获得labId对应的Lab信息
        Lab thisLab = labService.selectLabByLabId(labId);

        //设置检查标记
        int passFlag = 1;

        for (Integer willOrderWeek : orderWeek) {
            for (Integer willWeekDays : weekDays) {
                for (Integer willClassNum : classNum) {
                    //如果不存在已安排预约，则直接检查人数
                    if (labOrderList.size() == 0) {
                        if (thisLab.getLabPeoCount() < peoCount) {
                            passFlag = 0;
                        }
                    } else if (thisLab.getLabPeoCount() < peoCount + getPeoCountArranged(labOrderList, labId, willOrderWeek, willWeekDays, willClassNum)) {
                        passFlag = 0;
                    }
                }
            }
        }

        return passFlag;
    }


    public Order autoArrangeOrderCheck(Order order,String semester) {
        Order willArrangeOrder = order;
        //遍历三个志愿，按顺序遍历
        for (int j = 1; j < 4; j++) {
            //按一二三志愿顺序遍历
            OrderDetail willArrangeOrderDetail = new OrderDetail();
            for (OrderDetail orderDetail : willArrangeOrder.getOrderDetails()) {
                if (orderDetail.getType() == j) {
                    willArrangeOrderDetail = orderDetail;
                }
            }

            //若willArrangeOrderDetail内数据为空，则表明后续的志愿不符合要求，可直接跳出循环
            if (willArrangeOrderDetail.getFirstLab() == null) {
                break;
            }

            //对每个志愿，检查其填写的Lab是否合法
            //若合法，设置type为1、2、3
            //若不合法，设置type为0
            int passFlag = 0;
            if (checkLabAvailable(willArrangeOrderDetail.getFirstLab(), willArrangeOrderDetail.getFirstLabPeoCount(), willArrangeOrderDetail.getOrderWeek(), willArrangeOrderDetail.getWeekDays(), willArrangeOrderDetail.getClassNum(), semester) == 1) {

                //若第二实验室存在
                if (willArrangeOrderDetail.getSecondLab() != null) {
                    if (checkLabAvailable(willArrangeOrderDetail.getSecondLab(), willArrangeOrderDetail.getSecondLabPeoCount(), willArrangeOrderDetail.getOrderWeek(), willArrangeOrderDetail.getWeekDays(), willArrangeOrderDetail.getClassNum(), semester) == 1) {

                        //若第三实验室存在
                        if (willArrangeOrderDetail.getThirdLab() != null) {
                            if (checkLabAvailable(willArrangeOrderDetail.getThirdLab(), willArrangeOrderDetail.getThirdLabPeoCount(), willArrangeOrderDetail.getOrderWeek(), willArrangeOrderDetail.getWeekDays(), willArrangeOrderDetail.getClassNum(), semester) == 1) {
                                passFlag = 1;
                            } else {
                                passFlag = 0;
                            }
                        } else {
                            passFlag = 1;
                        }

                    } else {
                        passFlag = 0;
                    }
                } else {
                    passFlag = 1;
                }

            } else {
                passFlag = 0;
            }

            if (passFlag == 1) {
                willArrangeOrder.setPassFlag(j);
                break;
            }

        }
        return willArrangeOrder;
    }


    public int autoArrangeOrder(List<Order> rawOrderList, String semester) {
        List<Order> willArrangdOrderList = rawOrderList;
        //按照某种规则对OrderList排序
        Collections.sort(willArrangdOrderList, orderComparator);
        for (int i = 0; i < willArrangdOrderList.size(); i++) {
            orderService.updateOrder(autoArrangeOrderCheck(willArrangdOrderList.get(i),semester));
        }
        return 0;
    }

    public int autoArrangeLab(List<Order> rawOrderList, String semester){
        for(Order order : rawOrderList){
            Lab setLab = null;
            if(order.getIsLabArranged() == 0 && order.getIsAutoArrangeLab() == 1 && order.getPassFlag() == unArranged){
                List<Lab> labList = labService.selectLabByTag(order.getRequireTag());
                //统计可容纳的实验室数量
                int count = 0;
                Lab minUsageLab = labList.get(0);
                for(Lab lab : labList){
                    if(lab.getLabPeoCount() >= order.getClassPeoCount()){
                        count++;
                    }
                    if(lab.getLabUsage() < minUsageLab.getLabUsage()){
                        minUsageLab = lab;
                    }
                }

                //如果都不能容纳
                if(count == 0){
                    order.getOrderDetails().get(0).setFirstLab(minUsageLab.getId());
                    order.setIsLabArranged(1);
                    setLab = minUsageLab;
                }else {
                    //去除不能容纳的情况
                    Iterator<Lab> it = labList.iterator();
                    while(it.hasNext()){
                        Lab lab = it.next();
                        if(lab.getLabPeoCount() < order.getClassPeoCount()){
                            it.remove();
                        }
                    }

                    minUsageLab = labList.get(0);
                    for(Lab lab : labList){
                        if(lab.getLabUsage() < minUsageLab.getLabUsage()){
                            minUsageLab = lab;
                        }
                    }
                    order.getOrderDetails().get(0).setFirstLab(minUsageLab.getId());
                    order.setIsLabArranged(1);
                    setLab = minUsageLab;
                }
            }

            if(setLab != null){
                setLab.setLabUsage(setLab.getLabUsage() + labService.calculateLabUsageMethod(order.getOrderDetails().get(0)));
                labService.updateLab(setLab);
                orderService.updateOrder(order);
            }

        }
        return 0;
    }


    //排序规则
    Comparator<Order> orderComparator = new Comparator<Order>() {
        public int compare(Order o1, Order o2) {
            //计算一些数据
            //1.课程级别（A类、B类、C类）
            int o1ClassLevel = getClassLevel(o1.getClassId());
            int o2ClassLevel = getClassLevel(o2.getClassId());
            int subClassLevel = o1ClassLevel - o2ClassLevel;
            //2.课程人数
            int subClassPeo = o1.getClassPeoCount() - o2.getClassPeoCount();
            //3.提交时间
            long subDate = o1.getCreateTime().getTime() - o2.getCreateTime().getTime();

            int compareResult = 0;

            if (subClassLevel != 0) {
                compareResult = (subClassLevel > 0) ? 3 : -1;
            } else if (subClassPeo != 0) {
                compareResult = (subClassPeo > 0) ? 2 : -2;
            } else if (subDate != 0) {
                compareResult = (subDate > 0) ? 1 : -3;
            }

            return compareResult;
        }
    };

    public int getClassLevel(String classId) {
        int ClassA_Level = 26;
        int ClassS_Level = 25;
        int ClassB_Level = 24;
        int ClassC_Level = 23;
        int ClassD_Level = 22;
        int ClassE_Level = 21;
        int ClassF_Level = 20;
        int ClassG_Level = 19;
        int ClassH_Level = 18;
        int ClassI_Level = 17;
        int ClassJ_Level = 16;
        int ClassK_Level = 15;
        int ClassL_Level = 14;
        int ClassM_Level = 13;
        int ClassN_Level = 12;
        int ClassO_Level = 11;
        int ClassP_Level = 10;
        int ClassQ_Level = 9;
        int ClassR_Level = 8;
        int ClassT_Level = 7;
        int ClassU_Level = 6;
        int ClassV_Level = 5;
        int ClassW_Level = 4;
        int ClassX_Level = 3;
        int ClassY_Level = 2;
        int ClassZ_Level = 1;

        if (classId.contains("A")) {
            return ClassA_Level;
        } else if (classId.contains("B")) {
            return ClassB_Level;
        } else if (classId.contains("C")) {
            return ClassC_Level;
        } else if (classId.contains("D")) {
            return ClassD_Level;
        } else if (classId.contains("E")) {
            return ClassE_Level;
        } else if (classId.contains("F")) {
            return ClassF_Level;
        } else if (classId.contains("G")) {
            return ClassG_Level;
        } else if (classId.contains("H")) {
            return ClassH_Level;
        } else if (classId.contains("I")) {
            return ClassI_Level;
        } else if (classId.contains("J")) {
            return ClassJ_Level;
        } else if (classId.contains("K")) {
            return ClassK_Level;
        } else if (classId.contains("L")) {
            return ClassL_Level;
        } else if (classId.contains("M")) {
            return ClassM_Level;
        } else if (classId.contains("N")) {
            return ClassN_Level;
        } else if (classId.contains("O")) {
            return ClassO_Level;
        } else if (classId.contains("P")) {
            return ClassP_Level;
        } else if (classId.contains("Q")) {
            return ClassQ_Level;
        } else if (classId.contains("R")) {
            return ClassR_Level;
        } else if (classId.contains("S")) {
            return ClassS_Level;
        } else if (classId.contains("T")) {
            return ClassT_Level;
        } else if (classId.contains("U")) {
            return ClassU_Level;
        } else if (classId.contains("V")) {
            return ClassV_Level;
        } else if (classId.contains("W")) {
            return ClassW_Level;
        } else if (classId.contains("X")) {
            return ClassX_Level;
        } else if (classId.contains("Y")) {
            return ClassY_Level;
        } else if (classId.contains("Z")) {
            return ClassZ_Level;
        } else {
            return 0;
        }
    }


}
