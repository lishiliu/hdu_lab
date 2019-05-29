package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.entity.Class;

import java.util.List;

/**
 * Created by liyan on 2018/1/30.
 */
public interface ClassService {
    int addClass(Class course);//添加课程

    int updateClass(Class course);//更新课程信息

    int deleteClass(Class course);//删除课程

    List<Class> selectClassByUsername(String Username);//根据教师号查询课程

    List<Class> selectClassByUsernameAndSemester(String UserName, String semester);//根据输入学期，查询教师号对应的课程

}
