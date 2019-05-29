package cn.charlesxu.LabManager.dao.impl;

import cn.charlesxu.LabManager.dao.OrderDetailDao;
import cn.charlesxu.LabManager.entity.OrderDetail;
import cn.charlesxu.LabManager.mapper.OrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyan on 2018/1/31.
 */
@Repository
public class OrderDetailDaoImpl implements OrderDetailDao {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public int insert(OrderDetail orderDetail) {
        listToString(orderDetail);
        int count = orderDetailMapper.insert(orderDetail);
        return count;
    }

    public int batchInsert(List<OrderDetail> orderDetailList) {
        for (int i = 0; i < orderDetailList.size(); i++) {
            listToString(orderDetailList.get(i));
        }
        int count = orderDetailMapper.batchInsert(orderDetailList);
        return count;
    }

    public int update(OrderDetail orderDetail) {
        listToString(orderDetail);
        return orderDetailMapper.update(orderDetail);
    }

    public int deleteByOrderId(Integer orderId) {
        return orderDetailMapper.deleteByOrderId(orderId);
    }

    public OrderDetail selectById(Integer id) {
        OrderDetail orderDetail = orderDetailMapper.selectById(id);
        stringToList(orderDetail);
        return orderDetail;
    }

    public List<OrderDetail> selectByOrderId(Integer orderId) {
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orderId);
        for (int i = 0; i < orderDetailList.size(); i++) {
            stringToList(orderDetailList.get(i));
        }
        return orderDetailList;
    }

    public OrderDetail selectByOrderIdAndType(Integer orderId, Integer type) {
        return orderDetailMapper.selectByOrderIdAndType(orderId, type);
    }

    /*
     *将OrderDetail中的orderWeek、weekDays、classNum等List属性转换为字符串，以存储到数据库
     */
    public void listToString(OrderDetail orderDetail) {
        String orderWeek = new String();
        String weekDays = new String();
        String classNum = new String();
        if (orderDetail.getOrderWeek() != null) {
            for (int i = 0; i < orderDetail.getOrderWeek().size(); i++) {
                String m = orderDetail.getOrderWeek().get(i).toString();
                if (i == 0) {
                    orderWeek = m;
                } else {
                    orderWeek = orderWeek + "," + m;
                }
            }
        }
        if (orderDetail.getWeekDays() != null) {
            for (int i = 0; i < orderDetail.getWeekDays().size(); i++) {
                String m = orderDetail.getWeekDays().get(i).toString();
                if (i == 0) {
                    weekDays = m;
                } else {
                    weekDays = weekDays + "," + m;
                }
            }
        }
        if (orderDetail.getClassNum() != null) {
            for (int i = 0; i < orderDetail.getClassNum().size(); i++) {
                String m = orderDetail.getClassNum().get(i).toString();
                if (i == 0) {
                    classNum = m;
                } else {
                    classNum = classNum + "," + m;
                }
            }
        }
        orderDetail.setOrderWeekString(orderWeek);
        orderDetail.setWeekDaysString(weekDays);
        orderDetail.setClassNumString(classNum);
    }

    /*
     *将OrderDetail中的orderWeekString、weekDaysString、classNumString等字符串属性转换为数组
     */
    public void stringToList(OrderDetail orderDetail) {
        List<Integer> orderWeek = new ArrayList<Integer>();
        List<Integer> weekDays = new ArrayList<Integer>();
        List<Integer> classNum = new ArrayList<Integer>();
        //orderWeekString转换为orderWeek数组
        String[] arr = orderDetail.getOrderWeekString().split(",");
        for (int i = 0; i < arr.length; i++) {
            int m = Integer.parseInt(arr[i]);
            orderWeek.add(m);
        }
        //weekDaysString转换为classWeek数组
        String[] arr1 = orderDetail.getWeekDaysString().split(",");
        for (int i = 0; i < arr1.length; i++) {
            int m = Integer.parseInt(arr1[i]);
            weekDays.add(m);
        }
        //classNumString转换为classNum数组
        String[] arr2 = orderDetail.getClassNumString().split(",");
        for (int i = 0; i < arr2.length; i++) {
            int m = Integer.parseInt(arr2[i]);
            classNum.add(m);
        }
        orderDetail.setOrderWeek(orderWeek);
        orderDetail.setWeekDays(weekDays);
        orderDetail.setClassNum(classNum);
    }


}
