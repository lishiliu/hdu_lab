package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.entity.QuartzTask;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface QuartzTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuartzTask record);

    int insertSelective(QuartzTask record);

    QuartzTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuartzTask record);

    ArrayList<QuartzTask> selectSelective(QuartzTask quartzTask);

    ArrayList<QuartzTask> getAllQuartzTask();
}