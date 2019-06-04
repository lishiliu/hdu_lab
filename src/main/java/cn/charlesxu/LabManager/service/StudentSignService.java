package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.entity.StudentSign;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToTeacher;

import java.util.Date;
import java.util.List;

/**
 * Created by liyan on 2019/6/2.
 */
public interface StudentSignService {
    int signIn(StudentSign studentSign);//学生签到

    int updateStatus(String teacherId,String classId,int status);//开始签到

    List<StudentSignInfoToTeacher> getSignRecordByTeacherAndClassAndWeek(String teacherId, String classId);//教师学生签到记录查看

    int selectCountByRequest(StudentSign studentSign);

    int addStudent(String teacherId, String classId,String studentId,String studentName);

    List<StudentSign> getSignRecordByClassAndWeekAndLabId(String classId,String week,int labId);//实验室管理员签到记录查看

    List<StudentSign> getSignRecordByTime(Date startDate, Date endDate,int labId);//实验室管理员根据时间查询签到记录
}
