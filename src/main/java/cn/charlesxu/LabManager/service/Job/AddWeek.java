package cn.charlesxu.LabManager.service.Job;

import cn.charlesxu.LabManager.dao.ClassDao;
import cn.charlesxu.LabManager.dao.SemesterDao;
import cn.charlesxu.LabManager.dao.StudentSignDao;
import cn.charlesxu.LabManager.dao.SystemParameterDao;
import cn.charlesxu.LabManager.entity.Class;
import cn.charlesxu.LabManager.entity.Semester;
import cn.charlesxu.LabManager.entity.StudentSign;
import cn.charlesxu.LabManager.entity.SystemParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Charles Xu
 * Date: 2018/3/24
 * Time: 23:23
 */
@Component("addWeekJob")
public class AddWeek {
    @Autowired
    private SystemParameterDao systemParamterDao;

    @Autowired
    private StudentSignDao studentSignDao;

    @Autowired
    private SemesterDao semesterDao;

    @Autowired
    private ClassDao classDao;

    public void job1() {
        SystemParameter systemParameter = systemParamterDao.select();
        Semester semester=semesterDao.selectById(systemParameter.getThisSemesterId());
        int thisWeek=systemParameter.getThisWeek() + 1;
        systemParamterDao.updateByThisWeek(thisWeek);
       if(thisWeek>1){
           List<Class> classList=classDao.selectByStudentSignClassId(semester.getSemesterString());
           if(classList!=null){
               List<StudentSign> insertList=new ArrayList<>();
               for(Class course:classList){
                   if(course.getClassWeek().contains(thisWeek)){
                       StudentSign request=new StudentSign();
                       request.setClassId(course.getClassId());
                       List<StudentSign> studentSigns=studentSignDao.selectByRequest(request);
                       if(studentSigns!=null){
                           for(StudentSign studentSign:studentSigns){
                               studentSign.setStatus(0);
                               studentSign.setComputerNo(null);
                               studentSign.setWeekDays(null);
                               studentSign.setClassNum(null);
                               studentSign.setWorkDate(null);
                               studentSign.setCreateDate(getNowDateTime());
                               studentSign.setWeek(thisWeek);
                               studentSign.setBeginYear(semester.getBeginYear());
                               studentSign.setEndYear(semester.getEndYear());
                               studentSign.setTerm(semester.getTerm());
                               insertList.add(studentSign);
                           }
                       }
                   }
               }



           }
       }

    }
    public Date getNowDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
}
