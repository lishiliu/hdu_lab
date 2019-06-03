package cn.charlesxu.LabManager.service.impl;

import cn.charlesxu.LabManager.dao.SemesterDao;
import cn.charlesxu.LabManager.dao.StudentSignDao;
import cn.charlesxu.LabManager.dao.SystemParameterDao;
import cn.charlesxu.LabManager.entity.Semester;
import cn.charlesxu.LabManager.entity.StudentSign;
import cn.charlesxu.LabManager.entity.SystemParameter;
import cn.charlesxu.LabManager.service.StudentSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private SemesterDao semesterDao;

    @Autowired
    private SystemParameterDao systemParamterDao;

    @Override
    public int signIn(StudentSign studentSign) {
        List<StudentSign> studentSignList=studentSignDao.selectByRequest(studentSign);
        studentSign.setId(studentSignList.get(0).getId());
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
        return studentSignDao.updateStatusByClassIdAndWeekAndTeacher(request);
    }

    @Override
    public List<StudentSign> getSignRecordByTeacherAndClassAndWeek(String teacherId, String classId, String week) {
        return null;
    }

    @Override
    public List<StudentSign> getSignRecordByClassAndWeekAndLabId(String classId, String week, int labId) {
        return null;
    }

    @Override
    public List<StudentSign> getSignRecordByTime(Date startDate, Date endDate, int labId) {
        return null;
    }
}
