package cn.charlesxu.LabManager.service.impl;

import cn.charlesxu.LabManager.dao.SemesterDao;
import cn.charlesxu.LabManager.dao.StudentDao;
import cn.charlesxu.LabManager.dao.StudentSignDao;
import cn.charlesxu.LabManager.dao.SystemParameterDao;
import cn.charlesxu.LabManager.entity.Semester;
import cn.charlesxu.LabManager.entity.Student;
import cn.charlesxu.LabManager.entity.StudentSign;
import cn.charlesxu.LabManager.entity.define.SignStatusDefine;
import cn.charlesxu.LabManager.entity.SystemParameter;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToStudent;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToTeacher;
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

    @Override
    public int signIn(StudentSign studentSign) {
        List<StudentSign> studentSignList=studentSignDao.selectByRequest(studentSign);
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

    public Date getNowDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
}
