package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.entity.SystemParameter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by liyan on 2018/2/3.
 */
@Component
public interface SystemParameterMapper {

    /**
     * 向数据库更新thisSemesterId的接口
     *
     * @param thisSemesterId
     * @return 是否更新成功，成功为1，否则为0
     */
    int updateByThisSemesterId(@Param(value = "thisSemesterId") Integer thisSemesterId);

    /**
     * 向数据库更新nowOrderSemesterId的接口
     *
     * @param nowOrderSemesterId
     * @return 是否更新成功，成功为1，否则为0
     */
    int updateByNowOrderSemesterId(@Param(value = "nowOrderSemesterId") Integer nowOrderSemesterId);

    /**
     * 向数据库更新thisWeek的接口
     *
     * @param thisWeek
     * @return 是否更新成功，成功为1，否则为0
     */
    int updateByThisWeek(@Param(value = "thisWeek") Integer thisWeek);

    /**
     * 查询系统参数的接口
     *
     * @return SystemParameter
     */
    SystemParameter select();

}
