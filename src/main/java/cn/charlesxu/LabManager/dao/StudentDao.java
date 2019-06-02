package cn.charlesxu.LabManager.dao;


import cn.charlesxu.LabManager.entity.QuartzTask;
import cn.charlesxu.LabManager.entity.Student;

import java.util.ArrayList;


public interface StudentDao {
    int deleteById(Integer id);

    int insertSelective(Student record);

    Student selectById(Integer id);

    int updateById(Student record);

    ArrayList<Student> selectByRequest(Student request);


}
