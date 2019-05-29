package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.entity.Order;
import cn.charlesxu.LabManager.entity.form.Order1;
import cn.charlesxu.LabManager.entity.form.OrderDetail1;
import cn.charlesxu.LabManager.entity.form.SimpleOrder;

import java.util.List;

/**
 * Created by
 * User: Charles Xu
 * Date: 31/1/2018
 * Time: 11:26
 */
public interface OrderService {
    int addOrder(Order1 order1);

    int updateOrder(Order1 order1);

    int updateOrder(Order order);

    int deleteOrder(Integer orderId);

    List<Order> getOrderByUsername(String username);

    List<Order> getOrderByUsernameAndSemester(String username, String semester);

    List<Order> getOrderByLabId(int LabId);

    List<Order> getOrderByLabIdAndSemester(int LabId, String semester);

    Order selectOrderById(Integer id);

    //只查询预约单（不包含OrderDetail）
    SimpleOrder getOnlyOrder(Integer id);

    //根据userName只查询预约单（不包含OrderDetail）的接口
    List<SimpleOrder> getOnlyOrderByUserName(String userName);

    List<SimpleOrder> getOnlyOrderByUserNameAndSemester(String userName, String semester);

    List<SimpleOrder> getFinishedSimpleOrderListByUsername(String userName);//根据username查询安排预约单

    List<SimpleOrder> getFinishedSimpleOrderListByUsernameAndSemester(String userName, String semester);

    List<SimpleOrder> getUnfinishedSimpleOrderListByUsername(String userName);//根据username查询未安排预约单

    List<SimpleOrder> getUnfinishedSimpleOrderListByUsernameAndSemester(String userName, String semester);

    List<SimpleOrder> getFinishedSimpleOrderListByLabId(Integer labId);//根据labId查询安排预约单

    List<SimpleOrder> getFinishedSimpleOrderListByLabIdAndSemester(Integer labId, String semester);

    List<SimpleOrder> getUnfinishedSimpleOrderListByLabId(Integer labId);//根据labId查询未安排预约单

    List<SimpleOrder> getUnfinishedSimpleOrderListByLabIdAndSemester(Integer labId, String semester);

    List<SimpleOrder> getSimpleOrderListByLabId(Integer labId);

    List<SimpleOrder> getSimpleOrderListByLabIdAndSemester(Integer labId, String semesterString);

    List<OrderDetail1> getOrderDetailByOrderId(Integer orderId);

    //编辑预约（管理员端）
    int updateOrderByAdmin(Order1 order1);

    //自动安排预约（管理员端）
    int autoArrangerOrderByLabId(Integer labId);

    int autoArrangerOrderByLabIdAndSemester(Integer labId, String semesterString);

    List<Order> getFinishedOrderList(List<Order> orderList);

    List<Order> getUnfinishedOrderList(List<Order> orderList);

    Order1 orderToOrder1(Order order);

    List<Order1> orderToOrder1(List<Order> orderList);
}
