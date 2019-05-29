package cn.charlesxu.LabManager.dao.impl;

import cn.charlesxu.LabManager.dao.OrderDao;
import cn.charlesxu.LabManager.dao.OrderDetailDao;
import cn.charlesxu.LabManager.entity.Order;
import cn.charlesxu.LabManager.entity.OrderDetail;
import cn.charlesxu.LabManager.entity.form.SimpleOrder;
import cn.charlesxu.LabManager.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyan on 2018/1/31.
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailDao orderDetailDao;

    public int addOrder(Order order) {
        listToString(order);
        int count = orderMapper.insert(order);
        for (int i = 0; i < order.getOrderDetails().size(); i++) {
            order.getOrderDetails().get(i).setOrderId(order.getId());
        }
        int count1 = orderDetailDao.batchInsert(order.getOrderDetails());
        return count;
    }

    public int updateOrder(Order order) {
        listToString(order);
        int count = orderMapper.update(order);
        int orderId = order.getId();
        List<OrderDetail> orderDetails = orderDetailDao.selectByOrderId(orderId);
        for (int i = 0; i < order.getOrderDetails().size(); i++) {
            for (int j = 0; j < orderDetails.size(); j++) {
                if (order.getOrderDetails().get(i).getType() == orderDetails.get(j).getType()) {
                    order.getOrderDetails().get(i).setId(orderDetails.get(j).getId());
                    break;
                }
            }
        }
        for (int i = 0; i < order.getOrderDetails().size(); i++) {
            int count1 = orderDetailDao.update(order.getOrderDetails().get(i));
        }
        return count;
    }

    public int deleteOrder(int orderId) {
        int count = orderMapper.delete(orderId);
        int count1 = orderDetailDao.deleteByOrderId(orderId);
        return count;
    }

    public List<Order> selectOrderByUsername(String username) {
        List<Order> orderList = orderMapper.selectByUserName(username);
        stringToList(orderList);
        return orderList;
    }

    public List<Order> selectOrderByUsernameAndSemester(String userName, String semester) {
        semester = "_" + semester + "%";
        List<Order> orderList = orderMapper.selectOrderByUsernameAndSemester(userName, semester);
        stringToList(orderList);
        return orderList;
    }

    public List<Order> selectOrderByLabId(int LabId) {
        List<Order> orderList = orderMapper.selectByLabId(LabId);
        stringToList(orderList);
        return orderList;
    }

    public List<Order> selectOrderByLabIdAndSemester(int labId, String semester) {
        semester = "_" + semester + "%";
        List<Order> orderList = orderMapper.selectOrderByLabIdAndSemester(labId, semester);
        stringToList(orderList);
        return orderList;
    }

    public Order selectOrderById(Integer id) {
        Order order = orderMapper.selectById(id);
        stringToList(order);
        return order;
    }

    public SimpleOrder selectOnlyOrder(Integer id) {
        return orderMapper.selectOnlyOrder(id);
    }

    public List<SimpleOrder> selectOnlyOrderByUserName(String userName) {
        return orderMapper.selectOnlyOrderByUserName(userName);
    }

    public List<SimpleOrder> selectOnlyOrderByUserNameAndSemester(String userName, String semester) {
        semester = "_" + semester + "%";
        return orderMapper.selectOnlyOrderByUsernameAndSemester(userName, semester);
    }

    public List<SimpleOrder> selectOnlyOrderByLabId(Integer labId) {

        return orderMapper.selectOnlyOrderByLabId(labId);
    }

    public List<SimpleOrder> selectOnlyOrderByLabIdAndSemester(Integer labId, String semester) {
        semester = "_" + semester + "%";
        return orderMapper.selectOnlyOrderByLabIdAndSemester(labId, semester);
    }

    public List<Order> selectAllOrder() {
        List<Order> orderList = orderMapper.selectAllOrder();
        stringToList(orderList);
        return orderList;
    }

    public List<Order> selectAllOrderBySemester(String semester) {
        semester = "_" + semester + "%";
        List<Order> orderList = orderMapper.selectAllOrderBySemester(semester);
        stringToList(orderList);
        return orderList;
    }


    public void stringToList(Order order) {
        if (order.getRequireTagString() != null) {
            String[] arr = order.getRequireTagString().split(",");
            List<Integer> temp = new ArrayList<Integer>();
            for (int i = 0; i < arr.length; i++) {
                int m = Integer.parseInt(arr[i]);
                temp.add(m);
            }
            order.setRequireTag(temp);
        }
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            orderDetailDao.stringToList(orderDetail);
        }
    }

    public void listToString(Order order) {
        if (order.getRequireTag() != null) {
            order.setRequireTagString(StringUtils.collectionToDelimitedString(order.getRequireTag(), ","));
        }
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            orderDetailDao.listToString(orderDetail);
        }
    }

    public void stringToList(List<Order> orderList) {
        for (Order order : orderList) {
            stringToList(order);
        }
    }

    public void listToString(List<Order> orderList) {
        for (Order order : orderList) {
            listToString(order);
        }
    }
}
