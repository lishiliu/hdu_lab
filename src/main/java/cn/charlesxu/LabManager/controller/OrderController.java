package cn.charlesxu.LabManager.controller;

import cn.charlesxu.LabManager.entity.Order;
import cn.charlesxu.LabManager.entity.form.Order1;
import cn.charlesxu.LabManager.entity.form.OrderDetail1;
import cn.charlesxu.LabManager.entity.form.SimpleOrder;
import cn.charlesxu.LabManager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(maxAge = 3600)
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> addOrder(@RequestBody Order1 order1) {
        int result = orderService.addOrder(order1);
        Map<String, Object> modelMap = new HashMap<String, Object>();

        if (result > 0) {
            modelMap.put("result", result);
        } else {
            modelMap.put("result", "fail");
        }

        return modelMap;
    }

    @RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateOrder(@RequestBody Order1 order1) {
        int result = orderService.updateOrder(order1);
        Map<String, Object> modelMap = new HashMap<String, Object>();

        if (result > 0) {
            modelMap.put("result", result);
        } else {
            modelMap.put("result", "fail");
        }

        return modelMap;

    }

    @RequestMapping(value = "/updateOrderByAdmin", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateOrderByAdmin(@RequestBody Order1 order1) {
        int result = orderService.updateOrderByAdmin(order1);
        Map<String, Object> modelMap = new HashMap<String, Object>();

        if (result > 0) {
            modelMap.put("result", result);
        } else {
            modelMap.put("result", -1);
        }

        return modelMap;
    }

    @RequestMapping(value = "/autoArrangeOrderByLabId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> autoArrangeOrderByLabId(@RequestBody Map<String, Object> map) {
        Integer labId = new Integer(map.get("labId").toString());
        int result = orderService.autoArrangerOrderByLabId(labId);

        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (result > 0) {
            modelMap.put("result", result);
        } else {
            modelMap.put("result", -1);
        }

        return modelMap;
    }

    @RequestMapping(value = "/semester/autoArrangeOrderByLabId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateOrderByAdmin(@RequestBody Map<String, Object> map) {
        Integer labId = new Integer(map.get("labId").toString());
        String semesterString = map.get("semester").toString();
        int result = orderService.autoArrangerOrderByLabIdAndSemester(labId, semesterString);

        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (result > 0) {
            modelMap.put("result", result);
        } else {
            modelMap.put("result", -1);
        }

        return modelMap;
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> deleteOrder(@RequestBody Map<String, Object> map) {
        int result = orderService.deleteOrder(new Integer(map.get("id").toString()));
        Map<String, Object> modelMap = new HashMap<String, Object>();

        if (result > 0) {
            modelMap.put("result", result);
        } else {
            modelMap.put("result", "fail");
        }

        return modelMap;
    }

    @RequestMapping(value = "/getOrderById", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getOrderById(@RequestBody Map<String, Object> map) {
        Order order_1 = orderService.selectOrderById(new Integer(map.get("id").toString()));
        Order1 order1 = orderService.orderToOrder1(order_1);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("Order", order1);
        return modelMap;
    }

    @RequestMapping(value = "/getOrderByUsername", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getOrderByUsername(@RequestBody Map<String, Object> map) {
        List<Order> orderList = orderService.getOrderByUsername(map.get("userName").toString());
        List<Order1> order1List = orderService.orderToOrder1(orderList);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("Order", order1List);
        return modelMap;
    }

    @RequestMapping(value = "/semester/getOrderByUsername", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getOrderByUsernameAndSemester(@RequestBody Map<String, Object> map) {
        List<Order> orderList = orderService.getOrderByUsernameAndSemester(
                map.get("userName").toString(),
                map.get("semester").toString()
        );
        List<Order1> order1List = orderService.orderToOrder1(orderList);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("Order", order1List);
        return modelMap;
    }


    @RequestMapping(value = "/getSimpleOrderByUsername", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getSimpleOrderByUsername(@RequestBody Map<String, Object> map) {
        List<SimpleOrder> simpleOrderList = orderService.getOnlyOrderByUserName(map.get("userName").toString());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("simpleOrderList", simpleOrderList);
        return modelMap;
    }

    @RequestMapping(value = "/semester/getSimpleOrderByUsername", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getSimpleOrderByUsernameAndSemester(@RequestBody Map<String, Object> map) {
        List<SimpleOrder> simpleOrderList = orderService.getOnlyOrderByUserNameAndSemester(
                map.get("userName").toString(),
                map.get("semester").toString()
        );

        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("simpleOrderList", simpleOrderList);
        return modelMap;
    }


    @RequestMapping(value = "/getFinishedSimpleOrderListByUsername", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getFinishedSimpleOrderListByUsername(@RequestBody Map<String, Object> map) {
        List<SimpleOrder> finishedSimpleOrderList = orderService.getFinishedSimpleOrderListByUsername(
                map.get("userName").toString()
        );
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("SimpleOrder", finishedSimpleOrderList);
        return modelMap;
    }

    @RequestMapping(value = "/semester/getFinishedSimpleOrderListByUsername", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getFinishedSimpleOrderListByUsernameAndSemester(@RequestBody Map<String, Object> map) {
        List<SimpleOrder> finishedSimpleOrderList = orderService.getFinishedSimpleOrderListByUsernameAndSemester(
                map.get("userName").toString(),
                map.get("semester").toString()
        );
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("SimpleOrder", finishedSimpleOrderList);
        return modelMap;
    }

    @RequestMapping(value = "/getUnfinishedSimpleOrderListByUsername", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getUnfinishedSimpleOrderListByUsername(@RequestBody Map<String, Object> map) {
        List<SimpleOrder> unfinishedSimpleOrderList = orderService.getUnfinishedSimpleOrderListByUsername(
                map.get("userName").toString()
        );
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("SimpleOrder", unfinishedSimpleOrderList);
        return modelMap;
    }

    @RequestMapping(value = "/semester/getUnfinishedSimpleOrderListByUsername", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getUnfinishedSimpleOrderListByUsernameAndSemester(@RequestBody Map<String, Object> map) {
        List<SimpleOrder> unfinishedSimpleOrderList = orderService.getUnfinishedSimpleOrderListByUsernameAndSemester(
                map.get("userName").toString(),
                map.get("semester").toString()
        );
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("SimpleOrder", unfinishedSimpleOrderList);
        return modelMap;
    }

    @RequestMapping(value = "/getOrderByLabId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getOrderByLabId(@RequestBody Map<String, Object> map) {
        List<Order> orderList = orderService.getOrderByLabId(new Integer(map.get("labId").toString()));
        List<Order1> order1List = orderService.orderToOrder1(orderList);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("Order", order1List);
        return modelMap;
    }

    @RequestMapping(value = "/semester/getOrderByLabId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getOrderByLabIdAndSemester(@RequestBody Map<String, Object> map) {
        List<Order> orderList = orderService.getOrderByLabIdAndSemester(
                new Integer(map.get("labId").toString()),
                map.get("semester").toString()
        );
        List<Order1> order1List = orderService.orderToOrder1(orderList);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("Order", order1List);
        return modelMap;
    }

    @RequestMapping(value = "/getFinishedSimpleOrderListByLabId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getFinishedSimpleOrderListByLabId(@RequestBody Map<String, Object> map) {
        List<SimpleOrder> simpleOrderList = orderService.getFinishedSimpleOrderListByLabId(new Integer(map.get("labId").toString()));
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("SimpleOrder", simpleOrderList);
        return modelMap;
    }

    @RequestMapping(value = "/semester/getFinishedSimpleOrderListByLabId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getFinishedSimpleOrderListByLabIdAndSemester(@RequestBody Map<String, Object> map) {
        List<SimpleOrder> simpleOrderList = orderService.getFinishedSimpleOrderListByLabIdAndSemester(
                new Integer(map.get("labId").toString()),
                map.get("semester").toString()
        );
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("SimpleOrder", simpleOrderList);
        return modelMap;
    }

    @RequestMapping(value = "/getUnfinishedSimpleOrderListByLabId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getUnfinishedSimpleOrderListByLabId(@RequestBody Map<String, Object> map) {
        List<SimpleOrder> simpleOrderList = orderService.getUnfinishedSimpleOrderListByLabId(
                new Integer(map.get("labId").toString())
        );
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("SimpleOrder", simpleOrderList);
        return modelMap;
    }

    @RequestMapping(value = "/semester/getUnfinishedSimpleOrderListByLabId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getUnfinishedSimpleOrderListByLabIdAndSemester(@RequestBody Map<String, Object> map) {
        List<SimpleOrder> simpleOrderList = orderService.getUnfinishedSimpleOrderListByLabIdAndSemester(
                new Integer(map.get("labId").toString()),
                map.get("semester").toString()
        );
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("SimpleOrder", simpleOrderList);
        return modelMap;
    }

    @RequestMapping(value = "/getOrderDetailByOrderId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getOrderDetailByOrderId(@RequestBody Map<String, Object> map) {
        List<OrderDetail1> orderDetail1List = orderService.getOrderDetailByOrderId(new Integer(map.get("orderId").toString()));
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("orderDetail1List", orderDetail1List);
        return modelMap;
    }

}
