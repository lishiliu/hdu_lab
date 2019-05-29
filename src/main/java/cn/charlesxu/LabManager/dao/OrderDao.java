package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.entity.Order;
import cn.charlesxu.LabManager.entity.form.SimpleOrder;

import java.util.List;

public interface OrderDao {
    int addOrder(Order order);

    int updateOrder(Order order);

    int deleteOrder(int orderId);

    List<Order> selectOrderByUsername(String username);

    List<Order> selectOrderByUsernameAndSemester(String userName, String semester);

    List<Order> selectOrderByLabId(int LabId);

    List<Order> selectOrderByLabIdAndSemester(int labId, String semester);

    Order selectOrderById(Integer id);

    //只查询预约单（不包含OrderDetail）
    SimpleOrder selectOnlyOrder(Integer id);

    //根据userName只查询预约单（不包含OrderDetail）的接口
    List<SimpleOrder> selectOnlyOrderByUserName(String userName);

    List<SimpleOrder> selectOnlyOrderByUserNameAndSemester(String userName, String semester);

    //根据labId只查询预约单（不包含OrderDetail）的接口
    List<SimpleOrder> selectOnlyOrderByLabId(Integer labId);

    List<SimpleOrder> selectOnlyOrderByLabIdAndSemester(Integer labId, String semester);

    List<Order> selectAllOrder();

    List<Order> selectAllOrderBySemester(String semester);

}
