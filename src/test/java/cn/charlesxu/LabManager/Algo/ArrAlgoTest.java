package cn.charlesxu.LabManager.Algo;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.dao.OrderDao;
import cn.charlesxu.LabManager.entity.Order;
import cn.charlesxu.LabManager.entity.form.SimpleOrder;
import cn.charlesxu.LabManager.service.OrderService;
import cn.charlesxu.LabManager.service.algorithm.ArrangeAlgorithm;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by
 * User: Charles Xu
 * Date: 6/2/2018
 * Time: 20:02
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArrAlgoTest extends BaseTest {
    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderService orderService;

    @Autowired
    ArrangeAlgorithm arrangeAlgorithm;

    @Test
    public void arrtest() {
        List<Order> rawOrderList = orderService.getOrderByLabId(7);
        rawOrderList = orderService.getUnfinishedOrderList(rawOrderList);
        arrangeAlgorithm.autoArrangeOrder(rawOrderList, "2017-2018-2");
    }

    @Test
    public void fTest() {
        List<SimpleOrder> rawOrderList = orderService.getFinishedSimpleOrderListByLabId(6);
        System.out.println("success");
    }

    @Test
    public void labTest() {
        List<Order> rawOrderList = orderDao.selectAllOrderBySemester("2013-2014-2");
        arrangeAlgorithm.autoArrangeLab(rawOrderList,"2013-2014-2");
    }


}
