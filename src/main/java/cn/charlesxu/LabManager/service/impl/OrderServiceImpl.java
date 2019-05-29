package cn.charlesxu.LabManager.service.impl;

import cn.charlesxu.LabManager.dao.OrderDao;
import cn.charlesxu.LabManager.dao.OrderDetailDao;
import cn.charlesxu.LabManager.dao.SystemParameterDao;
import cn.charlesxu.LabManager.entity.Order;
import cn.charlesxu.LabManager.entity.OrderDetail;
import cn.charlesxu.LabManager.entity.Semester;
import cn.charlesxu.LabManager.entity.SystemParameter;
import cn.charlesxu.LabManager.entity.form.Order1;
import cn.charlesxu.LabManager.entity.form.OrderDetail1;
import cn.charlesxu.LabManager.entity.form.SimpleOrder;
import cn.charlesxu.LabManager.service.OrderService;
import cn.charlesxu.LabManager.service.SemesterService;
import cn.charlesxu.LabManager.service.algorithm.ArrangeAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import static cn.charlesxu.LabManager.entity.define.OrderDefine.*;

/**
 * Created by
 * User: Charles Xu
 * Date: 31/1/2018
 * Time: 11:57
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private SystemParameterDao systemParameterDao;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    ArrangeAlgorithm arrangeAlgorithm;

    //--------------------------------- 添加预约部分 ---------------------------------//
    public int addOrder(Order1 order1) {
        order1.setPassFlagString(unArrangedString);
        order1.setCreateTime(getNowDateTime());
        order1.setLastModify(getNowDateTime());
        return orderDao.addOrder(order1ToOrder(order1));
    }

    //--------------------------------- 修改预约部分 ---------------------------------//
    public int updateOrder(Order1 order1) {
        order1.setLastModify(getNowDateTime());
        return orderDao.updateOrder(order1ToOrder(order1));
    }

    public int updateOrder(Order order){
        order.setLastModify(getNowDateTime());
        return orderDao.updateOrder(order);
    }

    /*
     * 编辑预约（管理员端）
     */
    public int updateOrderByAdmin(Order1 order1) {
        Order order = order1ToOrder(order1);
        order.setLastModify(getNowDateTime());

        //从ClassId中读取学期信息
        String semester = order.getClassId().substring(1, 11);
        order = arrangeAlgorithm.autoArrangeOrderCheck(order, semester);
        if (order.getPassFlag() == 0) {
            return -1;
        } else {
            return orderDao.updateOrder(order);
        }
    }

    //--------------------------------- 删除预约部分 ---------------------------------//
    public int deleteOrder(Integer orderId) {
        return orderDao.deleteOrder(orderId);
    }

    //--------------------------------- 自动安排预约 --------------------------------//

    /**
     * 自动安排预约（根据传入的LabId)
     * 注：这个方法安排的是 系统参数的 "正在预约学期"
     *
     * @param labId
     * @return Result Code 1
     */
    public int autoArrangerOrderByLabId(Integer labId) {
        //读取系统参数中的"正在预约学期"
        SystemParameter systemParameter = systemParameterDao.select();
        Semester semester = semesterService.getSemesterById(systemParameter.getNowOrderSemesterId());
        String semesterString = semester.getSemesterString();

        List<Order> rawOrderList = getOrderByLabIdAndSemester(labId, semesterString);
        rawOrderList = getUnfinishedOrderList(rawOrderList);

        //传入要安排的预约
        arrangeAlgorithm.autoArrangeOrder(rawOrderList, semester.getSemesterString());
        return 1;
    }

    public int autoArrangerOrderByLabIdAndSemester(Integer labId, String semesterString) {
        List<Order> rawOrderList = getOrderByLabIdAndSemester(labId, semesterString);
        rawOrderList = getUnfinishedOrderList(rawOrderList);

        //传入要安排的预约
        arrangeAlgorithm.autoArrangeOrder(rawOrderList, semesterString);
        return 1;
    }


    //---------------------------------- 查询方法 ----------------------------------//
    //1.Order部分
    public Order selectOrderById(Integer id) {
        Order order = orderDao.selectOrderById(id);
        return order;
    }

    public List<Order> getOrderByLabId(int LabId) {
        //读取系统参数中的"当前学期"
        String semesterString = semesterService.getNowSemesterString();

        List<Order> orderList1 = orderDao.selectOrderByLabIdAndSemester(LabId, semesterString);
        //此处添加方法对Order进行过滤
        orderList1 = checkOrder(orderList1, LabId);
        return orderList1;
    }

    public List<Order> getOrderByLabIdAndSemester(int LabId, String semester) {
        List<Order> orderList1 = orderDao.selectOrderByLabIdAndSemester(LabId, semester);
        //此处添加方法对Order进行过滤
        orderList1 = checkOrder(orderList1, LabId);
        return orderList1;
    }

    public List<Order> getOrderByUsername(String username) {
        //读取系统参数中的"当前学期"
        String semesterString = semesterService.getNowSemesterString();

        List<Order> orderList1 = orderDao.selectOrderByUsernameAndSemester(username, semesterString);
        //此处添加方法对Order进行过滤
        return orderList1;
    }

    public List<Order> getOrderByUsernameAndSemester(String username, String semester) {
        List<Order> orderList1 = orderDao.selectOrderByUsernameAndSemester(username, semester);
        return orderList1;
    }

    //2.SimpleOrder部分
    public SimpleOrder getOnlyOrder(Integer id) {
        SimpleOrder simpleOrder = orderDao.selectOnlyOrder(id);
        return simpleOrder;
    }

    public List<SimpleOrder> getOnlyOrderByUserName(String userName) {
        //读取系统参数中的"当前学期"
        String semesterString = semesterService.getNowSemesterString();

        List<SimpleOrder> simpleOrderList = orderDao.selectOnlyOrderByUserNameAndSemester(userName, semesterString);
        return simpleOrderList;
    }

    public List<SimpleOrder> getOnlyOrderByUserNameAndSemester(String userName, String semester) {
        List<SimpleOrder> simpleOrderList = orderDao.selectOnlyOrderByUserNameAndSemester(userName, semester);
        return simpleOrderList;
    }

    public List<SimpleOrder> getSimpleOrderListByLabId(Integer labId) {
        //读取系统参数中的"当前学期"
        String semesterString = semesterService.getNowSemesterString();

        List<SimpleOrder> simpleOrderList = orderDao.selectOnlyOrderByLabIdAndSemester(labId, semesterString);
        simpleOrderList = checkSimpleOrder(simpleOrderList, labId);
        return simpleOrderList;
    }

    public List<SimpleOrder> getSimpleOrderListByLabIdAndSemester(Integer labId, String semesterString) {
        List<SimpleOrder> simpleOrderList = orderDao.selectOnlyOrderByLabIdAndSemester(labId, semesterString);
        simpleOrderList = checkSimpleOrder(simpleOrderList, labId);
        return simpleOrderList;
    }

    public List<SimpleOrder> getFinishedSimpleOrderListByUsername(String userName) {
        List<SimpleOrder> simpleOrderList = getOnlyOrderByUserName(userName);
        simpleOrderList = getFinishedSimpleOrderList(simpleOrderList);
        return simpleOrderList;
    }

    public List<SimpleOrder> getFinishedSimpleOrderListByUsernameAndSemester(String userName, String semester) {
        List<SimpleOrder> simpleOrderList = getOnlyOrderByUserNameAndSemester(userName, semester);
        simpleOrderList = getFinishedSimpleOrderList(simpleOrderList);
        return simpleOrderList;
    }

    public List<SimpleOrder> getUnfinishedSimpleOrderListByUsername(String userName) {
        List<SimpleOrder> simpleOrderList = getOnlyOrderByUserName(userName);
        simpleOrderList = getUnfinishedSimpleOrderList(simpleOrderList);
        return simpleOrderList;
    }

    public List<SimpleOrder> getUnfinishedSimpleOrderListByUsernameAndSemester(String userName, String semester) {
        List<SimpleOrder> simpleOrderList = getOnlyOrderByUserNameAndSemester(userName, semester);
        simpleOrderList = getUnfinishedSimpleOrderList(simpleOrderList);
        return simpleOrderList;
    }

    public List<SimpleOrder> getFinishedSimpleOrderListByLabId(Integer labId) {
        List<SimpleOrder> simpleOrderList = getSimpleOrderListByLabId(labId);
        simpleOrderList = getFinishedSimpleOrderList(simpleOrderList);
        return simpleOrderList;
    }

    public List<SimpleOrder> getFinishedSimpleOrderListByLabIdAndSemester(Integer labId, String semester) {
        List<SimpleOrder> simpleOrderList = getSimpleOrderListByLabIdAndSemester(labId, semester);
        simpleOrderList = getFinishedSimpleOrderList(simpleOrderList);
        return simpleOrderList;
    }

    public List<SimpleOrder> getUnfinishedSimpleOrderListByLabId(Integer labId) {
        List<SimpleOrder> simpleOrderList = getSimpleOrderListByLabId(labId);
        simpleOrderList = getUnfinishedSimpleOrderList(simpleOrderList);
        return simpleOrderList;
    }

    public List<SimpleOrder> getUnfinishedSimpleOrderListByLabIdAndSemester(Integer labId, String semester) {
        List<SimpleOrder> simpleOrderList = getSimpleOrderListByLabIdAndSemester(labId, semester);
        simpleOrderList = getUnfinishedSimpleOrderList(simpleOrderList);
        return simpleOrderList;
    }

    //3.OrderDetail部分
    public List<OrderDetail1> getOrderDetailByOrderId(Integer orderId) {
        List<OrderDetail> orderDetailList = orderDetailDao.selectByOrderId(orderId);
        List<OrderDetail1> orderDetail1List = new ArrayList<OrderDetail1>();
        for (OrderDetail orderDetail : orderDetailList) {
            orderDetail1List.add(orderDetailToOrderDetail1(orderDetail));
        }
        return orderDetail1List;
    }

    //-------------------------------- 数据处理部分 ---------------------------------//

    /*
     * 过滤已安排预约单
     */
    public List<Order> getFinishedOrderList(List<Order> orderList) {
        List<Order> finishedOrderList = new ArrayList<Order>();
        for (Order order : orderList) {
            if (order.getPassFlag() != unArranged) {
                finishedOrderList.add(order);
            }
        }
        return finishedOrderList;
    }

    /*
     * 过滤未安排预约单
     */
    public List<Order> getUnfinishedOrderList(List<Order> orderList) {
        List<Order> unfinishedOrderList = new ArrayList<Order>();
        for (Order order : orderList) {
            if (order.getPassFlag() == unArranged) {
                unfinishedOrderList.add(order);
            }
        }
        return unfinishedOrderList;
    }

    /*
     * 过滤已安排预约单(不包含OrderDetail)
     */
    public List<SimpleOrder> getFinishedSimpleOrderList(List<SimpleOrder> simpleOrderList) {
        List<SimpleOrder> finishedSimpleOrderList = new ArrayList<SimpleOrder>();
        for (SimpleOrder simpleOrder : simpleOrderList) {
            if (simpleOrder.getPassFlag() != unArranged) {
                finishedSimpleOrderList.add(simpleOrder);
            }
        }
        return finishedSimpleOrderList;
    }

    /*
     * 过滤未安排预约单(不包含OrderDetail)
     */
    public List<SimpleOrder> getUnfinishedSimpleOrderList(List<SimpleOrder> simpleOrderList) {
        List<SimpleOrder> unfinishedSimpleOrderList = new ArrayList<SimpleOrder>();
        for (SimpleOrder simpleOrder : simpleOrderList) {
            if (simpleOrder.getPassFlag() == unArranged) {
                unfinishedSimpleOrderList.add(simpleOrder);
            }
        }
        return unfinishedSimpleOrderList;
    }

    /*
     * 过滤SimpleOrder中，已安排的Order中，取出了不是被安排的那个OrderDetail的情况
     * 目前发现这种情况只出现在根据LabId获取的情况里
     */

    public List<SimpleOrder> checkSimpleOrder(List<SimpleOrder> simpleOrderList, Integer labId) {
        //若安排的志愿中没有对应的LabId，过滤掉
        Iterator<SimpleOrder> it = simpleOrderList.iterator();
        while (it.hasNext()) {
            SimpleOrder simpleOrder = it.next();

            //如果是未安排预约的Order，直接跳过
            if (simpleOrder.getPassFlag() == 0) {
                continue;
            }

            OrderDetail orderDetail = orderDetailDao.selectByOrderIdAndType(simpleOrder.getId(), simpleOrder.getPassFlag());

            if (orderDetail.getFirstLab().intValue() == labId.intValue()) {
                continue;
            } else if (orderDetail.getSecondLab() != null && orderDetail.getSecondLab().intValue() == labId.intValue()) {
                continue;
            } else if (orderDetail.getThirdLab() != null && orderDetail.getThirdLab().intValue() == labId.intValue()) {
                continue;
            } else {
                it.remove();
            }
        }
        return simpleOrderList;
    }

    public List<Order> checkOrder(List<Order> orderList, Integer labId) {
        Iterator<Order> iterator = orderList.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();

            //若是未安排的预约，直接跳过
            if (order.getPassFlag() == 0) {
                continue;
            }

            OrderDetail orderDetail = null;
            for (OrderDetail orderDetail1 : order.getOrderDetails()) {
                //获得被安排的志愿
                if (orderDetail1.getType() == order.getPassFlag()) {
                    orderDetail = orderDetail1;
                }
            }
            //如果找不到被安排的志愿，从List中删去这个Order
            if (orderDetail == null) {
                iterator.remove();
            }

        }
        return orderList;
    }

    public Date getNowDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public String getNowDateTimeString() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDateTime = dateFormat.format(calendar.getTime());
        return nowDateTime;
    }

    public String passFlagToString(int passFlag) {
        switch (passFlag) {
            case unArranged:
                return unArrangedString;
            case ArrangedFirstOrder:
                return ArrangedFirstOrderString;
            case ArrangedSecondOrder:
                return ArrangedSecondOrderString;
            case ArrangedThirdOrder:
                return ArrangedThirdOrderString;
            case failArranged:
                return failArrangedString;
        }
        return "passFlagError!";
    }

    public int passFlagStringToInteger(String passFlagString) {
        if (passFlagString.equals(unArrangedString)) {
            return unArranged;
        }
        if (passFlagString.equals(ArrangedFirstOrderString)) {
            return ArrangedFirstOrder;
        }
        if (passFlagString.equals(ArrangedSecondOrderString)) {
            return ArrangedSecondOrder;
        }
        if (passFlagString.equals(ArrangedThirdOrderString)) {
            return ArrangedThirdOrder;
        }
        if (passFlagString.equals(failArrangedString)) {
            return failArranged;
        }
        return -2;
    }

    public Order1 orderToOrder1(Order order) {
        Order1 order1 = new Order1();
        order1.setId(order.getId());
        order1.setUserName(order.getUserName());
        order1.setClassId(order.getClassId());
        order1.setClassName(order.getClassName());
        order1.setClassPeoCount(order.getClassPeoCount());
        order1.setRemark(order.getRemark());
        order1.setPassFlagString(passFlagToString(order.getPassFlag()));
        order1.setCreateTime(order.getCreateTime());
        order1.setLastModify(order.getLastModify());
        List<OrderDetail1> orderDetail1List = new ArrayList<OrderDetail1>();
        for (int i = 0; i < order.getOrderDetails().size(); i++) {
            orderDetail1List.add(orderDetailToOrderDetail1(order.getOrderDetails().get(i)));
        }
        order1.setOrderDetails(orderDetail1List);
        return order1;
    }

    public Order order1ToOrder(Order1 order_1) {
        Order order = new Order();
        order.setId(order_1.getId());
        order.setUserName(order_1.getUserName());
        order.setClassId(order_1.getClassId());
        order.setClassName(order_1.getClassName());
        order.setClassPeoCount(order_1.getClassPeoCount());
        order.setRemark(order_1.getRemark());
        order.setPassFlag(passFlagStringToInteger(order_1.getPassFlagString()));
        order.setCreateTime(order_1.getCreateTime());
        order.setLastModify(order_1.getLastModify());
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        for (int i = 0; i < order_1.getOrderDetails().size(); i++) {
            if (order_1.getOrderDetails().get(i).getOrderWeek().size() != 0) {
                orderDetailList.add(orderDetail1ToOrderDetail(order_1.getOrderDetails().get(i)));
            }
        }
        order.setOrderDetails(orderDetailList);
        return order;
    }

    public OrderDetail1 orderDetailToOrderDetail1(OrderDetail orderDetail) {
        OrderDetail1 orderDetail1 = new OrderDetail1();
        //orderDetail1.setOrderId(orderDetail.getOrderId());
        List<Integer> lab = new ArrayList<Integer>();
        List<Integer> labArrangedPeoCount = new ArrayList<Integer>();
        lab.add(orderDetail.getFirstLab());
        labArrangedPeoCount.add(orderDetail.getFirstLabPeoCount());
        if (orderDetail.getSecondLab() != null) {
            lab.add(orderDetail.getSecondLab());
            labArrangedPeoCount.add(orderDetail.getSecondLabPeoCount());
        }
        if (orderDetail.getThirdLab() != null) {
            lab.add(orderDetail.getThirdLab());
            labArrangedPeoCount.add(orderDetail.getThirdLabPeoCount());
        }
        orderDetail1.setLab(lab);
        orderDetail1.setLabArrangedPeoCount(labArrangedPeoCount);
        orderDetail1.setOrderWeek(orderDetail.getOrderWeek());
        orderDetail1.setWeekDays(orderDetail.getWeekDays());
        orderDetail1.setClassNum(orderDetail.getClassNum());
        orderDetail1.setType(orderDetail.getType());
        return orderDetail1;
    }

    public OrderDetail orderDetail1ToOrderDetail(OrderDetail1 orderDetail1) {
        OrderDetail orderDetail = new OrderDetail();
        //orderDetail1.setOrderId(orderDetail.getOrderId());
        List<Integer> lab = orderDetail1.getLab();
        List<Integer> labArrangedPeoCount = orderDetail1.getLabArrangedPeoCount();
        int size = lab.size();
        if (size == 1) {
            orderDetail.setFirstLab(lab.get(0));
            orderDetail.setFirstLabPeoCount(labArrangedPeoCount.get(0));
        }
        if (size == 2) {
            orderDetail.setFirstLab(lab.get(0));
            orderDetail.setFirstLabPeoCount(labArrangedPeoCount.get(0));
            orderDetail.setSecondLab(lab.get(1));
            orderDetail.setSecondLabPeoCount(labArrangedPeoCount.get(1));
        }
        if (size == 3) {
            orderDetail.setFirstLab(lab.get(0));
            orderDetail.setFirstLabPeoCount(labArrangedPeoCount.get(0));
            orderDetail.setSecondLab(lab.get(1));
            orderDetail.setSecondLabPeoCount(labArrangedPeoCount.get(1));
            orderDetail.setThirdLab(lab.get(2));
            orderDetail.setThirdLabPeoCount(labArrangedPeoCount.get(2));
        }
        orderDetail.setOrderWeek(orderDetail1.getOrderWeek());
        orderDetail.setWeekDays(orderDetail1.getWeekDays());
        orderDetail.setClassNum(orderDetail1.getClassNum());
        orderDetail.setType(orderDetail1.getType());
        return orderDetail;
    }

    public List<Order1> orderToOrder1(List<Order> orderList) {
        List<Order1> order1List = new ArrayList<Order1>();
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            Order1 order1 = orderToOrder1(order);
            order1List.add(order1);
        }
        return order1List;
    }

}
