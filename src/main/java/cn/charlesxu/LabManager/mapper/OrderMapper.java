package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.entity.Order;
import cn.charlesxu.LabManager.entity.form.SimpleOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 预约单Dao接口
 * Created by liyan on 2018/1/28.
 */
@Component
public interface OrderMapper {
    /**
     * 向数据库插入单个预约单的接口
     *
     * @param order
     * @return 是否插入成功，成功为1，否则为0
     */
    int insert(Order order);

    /**
     * 向数据库更新预约单的接口
     *
     * @param order
     * @return 是否更新成功，成功为1，否则为0
     */
    int update(Order order);

    /**
     * 删除一个预约单的接口
     *
     * @param id
     * @return 是否删除成功，成功为1，否则为0
     */
    int delete(final Integer id);

    /**
     * 只查询预约单（不包含OrderDetail）的接口
     *
     * @param id
     * @return 预约单
     */
    SimpleOrder selectOnlyOrder(Integer id);

    /**
     * 根据userName只查询预约单（不包含OrderDetail）的接口
     *
     * @param userName
     * @return 预约单
     */
    List<SimpleOrder> selectOnlyOrderByUserName(String userName);

    List<SimpleOrder> selectOnlyOrderByUsernameAndSemester(@Param("userName") String userName, @Param("semester") String semester);

    /**
     * 根据labId只查询预约单（不包含OrderDetail）的接口
     *
     * @param labId
     * @return 预约单
     */
    List<SimpleOrder> selectOnlyOrderByLabId(Integer labId);

    List<SimpleOrder> selectOnlyOrderByLabIdAndSemester(@Param("labId") Integer labId, @Param("semester") String semester);

    /**
     * 查询预约单的接口
     *
     * @param id
     * @return 预约单
     */
    Order selectById(@Param("id") Integer id);

    /**
     * 根据教师号查询预约单的接口
     *
     * @param userName
     * @return 预约单
     */
    List<Order> selectByUserName(String userName);

    List<Order> selectOrderByUsernameAndSemester(@Param("userName") String userName, @Param("semester") String semester);

    /**
     * 根据实验室查询预约单的接口
     *
     * @param labId
     * @return 预约单
     */
    List<Order> selectByLabId(Integer labId);

    List<Order> selectOrderByLabIdAndSemester(@Param("labId") Integer labId, @Param("semester") String semester);

    List<Order> selectAllOrder();

    List<Order> selectAllOrderBySemester(@Param("semester") String semester);

}
