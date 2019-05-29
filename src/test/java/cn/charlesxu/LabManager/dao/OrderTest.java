package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.Order;
import cn.charlesxu.LabManager.entity.OrderDetail;
import cn.charlesxu.LabManager.entity.form.SimpleOrder;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liyan on 2018/1/31.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderTest extends BaseTest {
    @Autowired
    private OrderDao orderDao;

    @Test
    public void update() {
        OrderDetail orderDetail = new OrderDetail();
        //orderDetail.setId(1);
        //orderDetail.setOrderId(2);
        List<Integer> orderWeek = new ArrayList<Integer>();
        for (int i = 1; i < 16; i++) {
            orderWeek.add(i);
        }
        orderDetail.setOrderWeek(orderWeek);
        List<Integer> weekDays = new ArrayList<Integer>();
        weekDays.add(5);
        orderDetail.setWeekDays(weekDays);
        List<Integer> classNum = new ArrayList<Integer>();
        classNum.add(10);
        classNum.add(11);
        classNum.add(12);
        orderDetail.setClassNum(classNum);
        orderDetail.setFirstLab(321);
        orderDetail.setFirstLabPeoCount(11);
        orderDetail.setSecondLab(321);
        orderDetail.setSecondLabPeoCount(11);
        orderDetail.setThirdLab(321);
        orderDetail.setThirdLabPeoCount(11);
        orderDetail.setType(1);

        OrderDetail orderDetail1 = new OrderDetail();
        //orderDetail.setId(1);
        //orderDetail1.setOrderId(2);
        List<Integer> orderWeek1 = new ArrayList<Integer>();
        for (int i = 1; i < 16; i++) {
            orderWeek1.add(i);
        }
        orderDetail1.setOrderWeek(orderWeek1);
        List<Integer> weekDays1 = new ArrayList<Integer>();
        weekDays1.add(5);
        orderDetail1.setWeekDays(weekDays1);
        List<Integer> classNum1 = new ArrayList<Integer>();
        classNum1.add(10);
        classNum1.add(11);
        classNum1.add(12);
        orderDetail1.setClassNum(classNum1);
        orderDetail1.setFirstLab(321);
        orderDetail1.setFirstLabPeoCount(11);
        orderDetail1.setSecondLab(321);
        orderDetail1.setSecondLabPeoCount(11);
        orderDetail1.setThirdLab(321);
        orderDetail1.setThirdLabPeoCount(11);
        orderDetail1.setType(2);

        OrderDetail orderDetail2 = new OrderDetail();
        //orderDetail.setId(1);
        //orderDetail1.setOrderId(2);
        List<Integer> orderWeek2 = new ArrayList<Integer>();
        for (int i = 1; i < 16; i++) {
            orderWeek2.add(i);
        }
        orderDetail2.setOrderWeek(orderWeek2);
        List<Integer> weekDays2 = new ArrayList<Integer>();
        weekDays2.add(5);
        orderDetail2.setWeekDays(weekDays2);
        List<Integer> classNum2 = new ArrayList<Integer>();
        classNum2.add(10);
        classNum2.add(11);
        classNum2.add(12);
        orderDetail2.setClassNum(classNum2);
        orderDetail2.setFirstLab(321);
        orderDetail2.setFirstLabPeoCount(11);
        orderDetail2.setSecondLab(321);
        orderDetail2.setSecondLabPeoCount(11);
        orderDetail2.setThirdLab(321);
        orderDetail2.setThirdLabPeoCount(11);
        orderDetail2.setType(2);
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        orderDetails.add(orderDetail);
        orderDetails.add(orderDetail1);
        orderDetails.add(orderDetail2);
        Order order = new Order();
        order.setClassId("2017-2018-2 A555");
        order.setClassName("OS");
        order.setClassPeoCount(50);
        order.setUserName("2222");
        order.setRemark("55555");
        order.setPassFlag(1);
        order.setCreateTime(new Date());
        order.setLastModify(new Date());
        order.setOrderDetails(orderDetails);

        int count = orderDao.addOrder(order);
    }

    @Test
    public void selectByUserName() {
        List<Order> orderList = orderDao.selectOrderByUsername("40392");
        Assert.assertTrue(orderList != null);
    }

    @Test
    public void selectByUserNameAndSemester() {
        List<Order> orderList = orderDao.selectOrderByUsernameAndSemester("40387", "2017-2018-2");
        Assert.assertTrue(orderList != null);
    }

    @Test
    public void selectByLabId() {
        List<Order> orderList = orderDao.selectOrderByLabId(6);
        Assert.assertTrue(orderList != null);
    }

    @Test
    public void selectByLabIdAndSemester() {
        List<Order> orderList = orderDao.selectOrderByLabIdAndSemester(6, "2017-2018-2");
        Assert.assertTrue(orderList != null);
    }

    @Test
    public void selectById() {
        Order order = orderDao.selectOrderById(38);
        Assert.assertTrue(order != null);
    }

    @Test
    public void selectOnlyOrder() {
        SimpleOrder simpleOrder = orderDao.selectOnlyOrder(38);
        Assert.assertTrue(simpleOrder != null);
    }

    @Test
    public void selectOnlyOrderByUserName() {
        List<SimpleOrder> simpleOrderList = orderDao.selectOnlyOrderByUserName("40392");
        Assert.assertTrue(simpleOrderList != null);
    }

    @Test
    public void selectOnlyOrderByUserNameAndSemester() {
        List<SimpleOrder> simpleOrderList = orderDao.selectOnlyOrderByUserNameAndSemester("40387", "2017-2018-2");
        Assert.assertTrue(simpleOrderList != null);
    }


    @Test
    public void selectOnlyOrderByLabId() {
        List<SimpleOrder> simpleOrderList = orderDao.selectOnlyOrderByLabId(6);
        Assert.assertTrue(simpleOrderList != null);
    }

    @Test
    public void selectOnlyOrderByLabIdAndSemester() {
        List<SimpleOrder> simpleOrderList = orderDao.selectOnlyOrderByLabIdAndSemester(6, "2017-2018-2");
        Assert.assertTrue(simpleOrderList != null);
    }
}
