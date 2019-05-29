package cn.charlesxu.LabManager.dao.impl;

import cn.charlesxu.LabManager.dao.SystemParameterDao;
import cn.charlesxu.LabManager.entity.SystemParameter;
import cn.charlesxu.LabManager.mapper.SystemParameterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by liyan on 2018/2/3.
 */
@Repository
public class SystemParameterDaoImpl implements SystemParameterDao {

    @Autowired
    private SystemParameterMapper systemParameterMapper;

    public int updateByThisSemesterId(Integer thisSemesterId) {
        return systemParameterMapper.updateByThisSemesterId(thisSemesterId);
    }

    public int updateByNowOrderSemesterId(Integer nowOrderSemesterId) {
        return systemParameterMapper.updateByNowOrderSemesterId(nowOrderSemesterId);
    }

    public int updateByThisWeek(Integer thisWeek) {
        return systemParameterMapper.updateByThisWeek(thisWeek);
    }

    public SystemParameter select() {
        return systemParameterMapper.select();
    }
}
