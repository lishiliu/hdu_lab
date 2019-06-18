package cn.charlesxu.LabManager.dao.impl;

import cn.charlesxu.LabManager.dao.QuartzTaskDao;
import cn.charlesxu.LabManager.entity.QuartzTask;
import cn.charlesxu.LabManager.mapper.QuartzTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liyan on 2019/5/31.
 */
@Repository
public class QuartzTaskDaoImpl implements QuartzTaskDao{
    @Autowired
    private QuartzTaskMapper quartzTaskMapper;

    public int deleteById(Integer id) {
        return quartzTaskMapper.deleteByPrimaryKey(id);
    }

    public int insert(QuartzTask record) {
        record.setCreateTime(getNowDateTime());
        record.setUpdateTime(getNowDateTime());
        return quartzTaskMapper.insert(record);
    }

    public int insertSelective(QuartzTask record) {
        return quartzTaskMapper.insertSelective(record);
    }

    public QuartzTask selectById(Integer id) {
        return quartzTaskMapper.selectByPrimaryKey(id);
    }

    public int updateById(QuartzTask record) {
        record.setUpdateTime(getNowDateTime());
        return updateById(record);
    }

    public ArrayList<QuartzTask> selectSelective(QuartzTask quartzTask) {
        return quartzTaskMapper.selectSelective(quartzTask);
    }

    public ArrayList<QuartzTask> getAllQuartzTask() {
        return quartzTaskMapper.getAllQuartzTask();
    }

    public Date getNowDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
}
