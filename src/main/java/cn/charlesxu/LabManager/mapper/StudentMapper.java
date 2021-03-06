package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    ArrayList<Student> selectByRequest(Student request);
}