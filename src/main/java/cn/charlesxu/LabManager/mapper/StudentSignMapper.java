package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.entity.StudentSign;
import org.springframework.stereotype.Component;

@Component
public interface StudentSignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentSign record);

    int insertSelective(StudentSign record);

    StudentSign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentSign record);

    int updateByPrimaryKey(StudentSign record);
}