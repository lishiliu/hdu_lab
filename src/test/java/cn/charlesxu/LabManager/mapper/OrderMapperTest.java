package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.Order;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by liyan on 2018/1/31.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderMapperTest extends BaseTest {
    @Autowired
    OrderMapper orderMapper;

    @Test
    public void selectByUserName() {
        List<Order> orderList = orderMapper.selectByUserName("2302");
        Assert.assertTrue(orderList != null);
    }

    @Test
    public void selectByLabId() {
        List<Order> orderList = orderMapper.selectByLabId(321);
        Assert.assertTrue(orderList != null);
    }
}
