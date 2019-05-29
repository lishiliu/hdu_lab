package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.entity.Semester;
import org.springframework.stereotype.Component;

@Component
public interface SemesterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Semester record);

    int insertSelective(Semester record);

    Semester selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Semester record);

    int updateByPrimaryKey(Semester record);
}