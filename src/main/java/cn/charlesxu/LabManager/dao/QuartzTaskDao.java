package cn.charlesxu.LabManager.dao;


import cn.charlesxu.LabManager.entity.QuartzTask;

import java.util.ArrayList;


public interface QuartzTaskDao {
    int deleteById(Integer id);

    int insert(QuartzTask record);

    int insertSelective(QuartzTask record);

    QuartzTask selectById(Integer id);

    int updateById(QuartzTask record);

    ArrayList<QuartzTask> selectSelective(QuartzTask quartzTask);

    ArrayList<QuartzTask> getAllQuartzTask();
}
