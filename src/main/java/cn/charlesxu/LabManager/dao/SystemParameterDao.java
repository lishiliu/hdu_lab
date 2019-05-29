package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.entity.SystemParameter;

/**
 * Created by liyan on 2018/2/3.
 */
public interface SystemParameterDao {
    /**
     * 向数据库更新thisSemesterId的接口
     *
     * @param thisSemesterId
     * @return 是否更新成功，成功为1，否则为0
     */
    int updateByThisSemesterId(Integer thisSemesterId);

    /**
     * 向数据库更新nowOrderSemesterId的接口
     *
     * @param nowOrderSemesterId
     * @return 是否更新成功，成功为1，否则为0
     */
    int updateByNowOrderSemesterId(Integer nowOrderSemesterId);


    /**
     * 向数据库更新thisWeek的接口
     *
     * @param thisWeek
     * @return 是否更新成功，成功为1，否则为0
     */
    int updateByThisWeek(Integer thisWeek);


    /**
     * 查询系统参数的接口
     *
     * @return SystemParameter
     */
    SystemParameter select();
}
