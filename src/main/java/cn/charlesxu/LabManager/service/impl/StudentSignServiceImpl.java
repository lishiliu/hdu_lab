package cn.charlesxu.LabManager.service.impl;

import cn.charlesxu.LabManager.dao.*;
import cn.charlesxu.LabManager.entity.*;
import cn.charlesxu.LabManager.entity.define.SignStatusDefine;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToStudent;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToTeacher;
import cn.charlesxu.LabManager.service.Job.EndSign;
import cn.charlesxu.LabManager.service.Quartz.QuartzManager;
import cn.charlesxu.LabManager.service.StudentSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by liyan on 2019/6/2.
 */
@Service
public class StudentSignServiceImpl implements StudentSignService {
    @Autowired
    private StudentSignDao studentSignDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private SemesterDao semesterDao;

    @Autowired
    private SystemParameterDao systemParamterDao;

    @Autowired
    private QuartzManager quartzManager;

    @Autowired
    private QuartzTaskDao quartzTaskDao;

    @Override
    public int signIn(StudentSign studentSign) {
        StudentSign request=new StudentSign();
        request.setStudentId(studentSign.getStudentId());
        request.setClassId(studentSign.getClassId());
        request.setWeek(studentSign.getWeek());
        List<StudentSign> studentSignList=studentSignDao.selectByRequest(request);
        studentSign.setId(studentSignList.get(0).getId());
        studentSign.setStatus(2);
        return studentSignDao.updateById(studentSign);
    }

    @Override
    public int updateStatus(String teacherId, String classId,int status) {
        SystemParameter systemParameter = systemParamterDao.select();
        StudentSign request=new StudentSign();
        request.setTeacherId(teacherId);
        request.setClassId(classId);
        request.setWeek(systemParameter.getThisWeek());
        request.setStatus(status);
        request.setCreateDate(getNowDateTime());
        //定时任务
        String CRON = dateToCron(getNowDateTime());
        String JOB_NAME= teacherId + ":Status修改:" + getNowDateTime();
        String JOB_GROUP_NAME= "UpdateSignStatus_JOB_GROUP";
        quartzManager.addJob(EndSign.class, JOB_NAME, JOB_GROUP_NAME, CRON, request);
        QuartzTask quartzTask = new QuartzTask();
        quartzTask.setCron(CRON);
        quartzTask.setStatus(0);
        quartzTask.setStartDate(getNowDateTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(getNowDateTime());
        cal.add(Calendar.HOUR, 1);// 24小时制
        Date endDate = cal.getTime();
        quartzTask.setEndDate(endDate);
        quartzTask.setCreateTime(getNowDateTime());
        quartzTask.setUpdateTime(getNowDateTime());
        quartzTask.setClassName(EndSign.class.getName());
        quartzTask.setJobName(JOB_NAME);
        quartzTask.setJobGroupName(JOB_GROUP_NAME);
        quartzTaskDao.insertSelective(quartzTask);
        return studentSignDao.updateStatusByClassIdAndWeekAndTeacher(request);
    }

    @Override
    public List<StudentSignInfoToTeacher> getSignRecordByTeacherAndClassAndWeek(String teacherId, String classId,int week) {
        List<StudentSignInfoToTeacher> studentSignInfoToTeacherList=studentSignDao.selectStudentSignInfoToTeacher(teacherId,classId,week);
        for(int i=0;i<studentSignInfoToTeacherList.size();i++){
            if(studentSignInfoToTeacherList.get(i).getStatus()==0){
                studentSignInfoToTeacherList.get(i).setStatusString(SignStatusDefine.SignStatus_0);
            }
            if(studentSignInfoToTeacherList.get(i).getStatus()==1){
                studentSignInfoToTeacherList.get(i).setStatusString(SignStatusDefine.SignStatus_1);
            }
            if(studentSignInfoToTeacherList.get(i).getStatus()==2){
                studentSignInfoToTeacherList.get(i).setStatusString(SignStatusDefine.SignStatus_2);
            }
            if(studentSignInfoToTeacherList.get(i).getStatus()==3){
                studentSignInfoToTeacherList.get(i).setStatusString(SignStatusDefine.SignStatus_3);
            }
        }
        return studentSignInfoToTeacherList;
    }

    @Override
    public int selectCountByRequest(StudentSign studentSign) {
        return studentSignDao.selectCountByRequest(studentSign);
    }

    @Override
    public int addStudent(String teacherId, String classId, String studentId, String studentName) {
        Student student=new Student();
        student.setStudentId(studentId);
        List<Student> studentList=studentDao.selectByRequest(student);
        if(studentList.size()==0){
            student.setStudentName(studentName);
            student.setPassword(studentId);
            student.setRegTime(getNowDateTime());
            studentDao.insertSelective(student);
        }
        SystemParameter systemParameter = systemParamterDao.select();
        Semester semester=semesterDao.selectById(systemParameter.getThisSemesterId());
        StudentSign studentSign=new StudentSign();
        studentSign.setClassId(classId);
        studentSign.setTeacherId(teacherId);
        studentSign.setStudentId(studentId);
        studentSign.setStatus(0);
        studentSign.setComputerNo(null);
        studentSign.setWeekDays(null);
        studentSign.setClassNum(null);
        studentSign.setWorkDate(null);
        studentSign.setCreateDate(getNowDateTime());
        studentSign.setWeek(1);
        studentSign.setBeginYear(semester.getBeginYear());
        studentSign.setEndYear(semester.getEndYear());
        studentSign.setTerm(semester.getTerm());
        return studentSignDao.insertSelective(studentSign);
    }

    @Override
    public List<StudentSign> getSignRecordByClassAndWeekAndLabId(String classId, String week, int labId) {
        return null;
    }

    @Override
    public List<StudentSign> getSignRecordByTime(Date startDate, Date endDate, int labId) {
        return null;
    }

    @Override
    public List<StudentSignInfoToStudent> getCurrentSignTask(String studentId) {
        SystemParameter systemParameter = systemParamterDao.select();
        Semester semester=semesterDao.selectById(systemParameter.getThisSemesterId());
        return studentSignDao.selectStudentSignInfoToStudent(studentId,semester.getSemesterString(),systemParameter.getThisWeek(),1);
    }

    @Override
    public List<StudentSignInfoToStudent> getPastedSignTaskBySemester(String studentId, String semester) {
        return studentSignDao.selectHistoryStudentSignInfoToStudent(studentId,semester);
    }

    public Date getNowDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public String dateToCron(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);//获取年份
        int month = cal.get(Calendar.MONTH) + 1;//获取月份
        int day = cal.get(Calendar.DATE);//获取日
        int hour = cal.get(Calendar.HOUR_OF_DAY)+1;//小时
        int minute = cal.get(Calendar.MINUTE);//分
        String CRON = "0" + " " + minute + " " + hour + " " + day + " " + month + " " + "? " + year;
        return CRON;
    }
}
