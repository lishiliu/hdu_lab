package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.OrderDetail;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by liyan on 2018/1/29.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderDetailMapperTest extends BaseTest {

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Test
    //向数据库插入单个食物的测试用例，不需要提供id，返回值是1
    public void insert() {
        OrderDetail orderDetail = new OrderDetail();
        //orderDetail.setId(1);
        orderDetail.setOrderId(2);
        orderDetail.setOrderWeekString("123");
        orderDetail.setWeekDaysString("12");
        orderDetail.setClassNumString("345");
        orderDetail.setFirstLab(321);
        orderDetail.setFirstLabPeoCount(11);
        orderDetail.setSecondLab(321);
        orderDetail.setSecondLabPeoCount(11);
        orderDetail.setThirdLab(321);
        orderDetail.setThirdLabPeoCount(11);
        orderDetail.setType(1);
        int count = orderDetailMapper.insert(orderDetail);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void delete() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(1);
        orderDetail.setOrderId(2);
        orderDetail.setOrderWeekString("123");
        orderDetail.setWeekDaysString("12");
        orderDetail.setClassNumString("345");
        orderDetail.setFirstLab(321);
        orderDetail.setFirstLabPeoCount(11);
        orderDetail.setSecondLab(321);
        orderDetail.setSecondLabPeoCount(11);
        orderDetail.setThirdLab(321);
        orderDetail.setThirdLabPeoCount(11);
        orderDetail.setType(1);
        int count0 = orderDetailMapper.insert(orderDetail);
        int count = orderDetailMapper.delete(1);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void update() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(2);
        orderDetail.setOrderWeekString("123");
        orderDetail.setWeekDaysString("12");
        orderDetail.setClassNumString("345");
        orderDetail.setFirstLab(321);
        orderDetail.setFirstLabPeoCount(11);
        orderDetail.setSecondLab(321);
        orderDetail.setSecondLabPeoCount(11);
        orderDetail.setThirdLab(321);
        orderDetail.setThirdLabPeoCount(11);
        orderDetail.setType(1);
        int count0 = orderDetailMapper.insert(orderDetail);
        orderDetail.setId(4);
        orderDetail.setOrderWeekString("321");
        orderDetail.setWeekDaysString("21");
        orderDetail.setClassNumString("678");
        orderDetail.setFirstLab(123);
        orderDetail.setFirstLabPeoCount(10);
        int count = orderDetailMapper.update(orderDetail);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void selectById() {
        OrderDetail orderDetail = orderDetailMapper.selectById(5);
        Assert.assertTrue(orderDetail != null);
    }

    @Test
    public void selectByOrderId() {
        List<OrderDetail> orderDetail = orderDetailMapper.selectByOrderId(2);
        Assert.assertTrue(orderDetail != null);
    }

    @Test
    public void selectByOrderIdAndType() {
        OrderDetail orderDetail = orderDetailMapper.selectByOrderIdAndType(44, 2);
        Assert.assertTrue(orderDetail != null);
    }
}
