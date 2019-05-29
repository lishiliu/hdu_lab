package cn.charlesxu.LabManager.service.impl;

import cn.charlesxu.LabManager.dao.LabDao;
import cn.charlesxu.LabManager.dao.OrderDao;
import cn.charlesxu.LabManager.entity.Lab;
import cn.charlesxu.LabManager.entity.Order;
import cn.charlesxu.LabManager.entity.OrderDetail;
import cn.charlesxu.LabManager.entity.form.Lab1;
import cn.charlesxu.LabManager.entity.form.OrderForm2;
import cn.charlesxu.LabManager.service.LabService;
import cn.charlesxu.LabManager.service.OrderService;
import cn.charlesxu.LabManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LabServiceImpl implements LabService {

    @Autowired
    private LabDao labDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    //添加实验室
    public int addLab(Lab lab) {
        int result = labDao.addLab(lab);
        return result;
    }

    //根据实验室种类查询实验室列表
    public List<Lab> selectLabByType(String type) {
        List<Lab> labs = new ArrayList<Lab>();
        labs.addAll(labDao.selectByType(type));
        return labs;
    }

    //根据实验室种类查询实验室列表(Lab1)
    public List<Lab1> selectLab1ByType(List<String> labTypeList) {
        List<Lab> labs = new ArrayList<Lab>();
        for (String type : labTypeList) {
            labs.addAll(labDao.selectByType(type));
        }
        List<Lab1> lab1s = new ArrayList<Lab1>();
        //将Lab转换给Lab1
        for (int i = 0; i < labs.size(); i++) {
            lab1s.add(LabToLab1(labs.get(i)));
        }
        return lab1s;
    }

    //查询当前已预约情况
    public List<String> getLabOrderStatus(OrderForm2 orderForm2) {
        List<Order> orderList = orderDao.selectOrderByLabId(orderForm2.getLabId());
        List<String> statusStringList = new ArrayList<String>();

        //过滤Order中不正确的
        Iterator<Order> it = orderList.iterator();
        while (it.hasNext()) {
            Order order = it.next();
            int flag = 0;
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                if (orderDetail.getType() == order.getPassFlag()) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                it.remove();
            }
        }

        for (Order order : orderList) {
            List<OrderDetail> orderDetailList = order.getOrderDetails();
            //如果是已经安排的预约 只获取对应的那个OrderDetail
            for (OrderDetail orderDetail : orderDetailList) {
                if (compareIntegerList(orderDetail.getOrderWeek(), orderForm2.getWeek())) {
                    if (compareIntegerList(orderDetail.getWeekDays(), orderForm2.getWeekday())) {
                        if (compareIntegerList(orderDetail.getClassNum(), orderForm2.getClassNum())) {
                            String nickname = userService.getNicknameByUsername(order.getUserName());
                            String peoCount = "";
                            if (orderDetail.getFirstLab().intValue() == orderForm2.getLabId()) {
                                peoCount = orderDetail.getFirstLabPeoCount().toString();
                            } else if (orderDetail.getSecondLab().intValue() == orderForm2.getLabId()) {
                                peoCount = orderDetail.getSecondLabPeoCount().toString();
                            } else if (orderDetail.getThirdLab().intValue() == orderForm2.getLabId()) {
                                peoCount = orderDetail.getThirdLabPeoCount().toString();
                            }
                            statusStringList.add(nickname + " 已预约 " + orderDetail.getClassNum() + "节 " + peoCount + "人");
                            break;
                        }
                    }
                }
            }
        }

        if (statusStringList.size() == 0) {
            statusStringList.add("当前无预约");
        }
        return statusStringList;
    }

    //比较两个List中的内容(true表示有相同项，false表示无相同项）
    public boolean compareIntegerList(List<Integer> list1, List<Integer> list2) {
        Map<String, Integer> map = new HashMap<String, Integer>(list1.size() + list2.size());

        for (Integer integer : list1) {
            map.put(integer.toString(), 1);
        }
        for (Integer integer : list2) {
            Integer count = map.get(integer.toString());
            if (count != null) {
                map.put(integer.toString(), ++count);
            } else {
                map.put(integer.toString(), 1);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 2) {
                return true;
            }
        }
        return false;
    }

    //获得所有实验室种类
    public List<String> getAllLabType() {
        return labDao.getAllLabType();
    }

    //统计实验室工时
    public int calculateLabArrangedUsage(int labId, String semester){
        List<Order> orderList = orderService.getFinishedOrderList(orderService.getOrderByLabIdAndSemester(labId,semester));
        Lab lab = labDao.selectLabByLabId(labId);
        int sum = 0;
        for(Order order : orderList){
            for(OrderDetail orderDetail: order.getOrderDetails()){
                if(orderDetail.getType() == order.getPassFlag()){
                    sum += calculateLabUsageMethod(orderDetail);
                }
            }
        }
        lab.setLabUsage(sum);
        labDao.updateLab(lab);
        return 0;
    }

    public int calculateLabUsageMethod(OrderDetail orderDetail){
        return orderDetail.getWeekDays().size() * orderDetail.getOrderWeek().size();
    }


    public List<Lab> selectLabByTag(List<Integer> tagList){
        return labDao.selectByTag(tagList);
    }

    //获取所有实验列表
    public List<Lab1> getAllLab1() {
        List<Lab> labList = new ArrayList<Lab>();
        labList.addAll(labDao.selectAllLab());

        List<Lab1> lab1s = new ArrayList<Lab1>();
        //将Lab转换给Lab1
        for (int i = 0; i < labList.size(); i++) {
            lab1s.add(LabToLab1(labList.get(i)));
        }
        return lab1s;
    }

    public List<Lab1> getLab1ByAdminUserName(String adminUserName) {
        List<Lab> labList = labDao.selectByAdminUserName(adminUserName);
        List<Lab1> lab1List = new ArrayList<Lab1>();
        for (Lab lab : labList) {
            lab1List.add(LabToLab1(lab));
        }
        return lab1List;
    }

    //修改实验室信息

    public int updateLab(Lab lab) {
        return labDao.updateLab(lab);
    }

    //删除一个实验室

    public int deleteLab(Lab lab) {
        return labDao.deleteLab(lab);
    }

    public Lab1 getLabById(Integer labId) {
        Lab lab = labDao.selectLabByLabId(labId);
        return LabToLab1(lab);
    }

    //将Lab对象转换成Lab1
    public Lab1 LabToLab1(Lab lab) {
        Lab1 lab1 = new Lab1();
        lab1.setId(lab.getId());
        lab1.setUserName(lab.getAdminUserName());
        lab1.setAdminName(userService.getNicknameByUsername(lab.getAdminUserName()));
        lab1.setLabName(lab.getLabName());
        lab1.setLabType(lab.getLabType());
        lab1.setLabPeoCount(lab.getLabPeoCount());
        lab1.setLabBuild(lab.getLabBuild());
        lab1.setLabNumber(lab.getLabNumber());
        lab1.setLabIntroduce(lab.getLabIntroduce());
        return lab1;
    }

    public Lab selectLabByLabId(int labId) {
        return labDao.selectLabByLabId(labId);
    }
}
