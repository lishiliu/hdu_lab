package cn.charlesxu.LabManager.dao;


import cn.charlesxu.LabManager.entity.QuartzTask;
import cn.charlesxu.LabManager.entity.StudentSign;

import java.util.ArrayList;


public interface StudentSignDao {
    int deleteById(Integer id);

    int insertSelective(StudentSign record);

    StudentSign selectById(Integer id);

    int updateById(StudentSign record);

    ArrayList<StudentSign> selectByRequest(StudentSign request);
}
