package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.entity.OrderDetail;

import java.util.List;

/**
 * Created by liyan on 2018/1/31.
 */
public interface OrderDetailDao {
    /**
     * 向数据库插入单个预约单细则的接口
     *
     * @param orderDetail
     * @return 是否插入成功，成功为1，否则为0
     */
    int insert(OrderDetail orderDetail);

    /**
     * 批量插入
     *
     * @param orderDetailList
     * @return
     */
    int batchInsert(List<OrderDetail> orderDetailList);

    /**
     * 向数据库更新预约单细则的接口
     *
     * @param orderDetail
     * @return 是否更新成功，成功为1，否则为0
     */
    int update(OrderDetail orderDetail);

    /**
     * 根据orderId预约单细则的接口
     *
     * @param orderId
     * @return 是否删除成功，成功为1，否则为0
     */
    int deleteByOrderId(final Integer orderId);

    /**
     * 查询预约单细则的接口
     *
     * @param id
     * @return 预约单
     */
    OrderDetail selectById(Integer id);

    /**
     * 查询预约单细则的接口
     *
     * @param orderId
     * @return 预约单
     */
    List<OrderDetail> selectByOrderId(Integer orderId);

    /**
     * 查询预约单细则的接口
     *
     * @param orderId,type
     * @return 预约单
     */
    OrderDetail selectByOrderIdAndType(Integer orderId, Integer type);

    void listToString(OrderDetail orderDetail);

    void stringToList(OrderDetail orderDetail);
}
