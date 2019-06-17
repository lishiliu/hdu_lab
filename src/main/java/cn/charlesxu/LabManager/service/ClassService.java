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

    List<Class> selectClassByUsernameAndWeek(String username);//根据教师号获取当前周的课程

    List<Class> selectClassByStudentIdAndSemester(String studentId,String semester);//根据学生号获取学生课程

    List<Class> selectClassByUsernameAndSemester(String UserName, String semester);//根据输入学期，查询教师号对应的课程

    List<Class> selectClassByUsernameAndSemesterAndWeek(String UserName, String semester,Integer week);//根据输入学期，查询教师号对应的课程

    List<Class> selectClassToLabAdmin(Integer labId);//根据labId查询实验室课程

    List<Class> selectClassToLabAdminBySemester(Integer labId,String semester);//根据学期查询实验室课程

    List<Class> selectClassToLabAdminBySemesterAndWeek(Integer labId,String semester,Integer week);//根据学期查询实验室课程
}
