package cn.charlesxu.LabManager.dao.impl;

import cn.charlesxu.LabManager.dao.StudentDao;
import cn.charlesxu.LabManager.dao.StudentSignDao;
import cn.charlesxu.LabManager.entity.Student;
import cn.charlesxu.LabManager.entity.StudentSign;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToStudent;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToTeacher;
import cn.charlesxu.LabManager.mapper.StudentMapper;
import cn.charlesxu.LabManager.mapper.StudentSignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by liyan on 2019/5/31.
 */
@Repository
public class StudentSignDaoImpl implements StudentSignDao{
    @Autowired
    private StudentSignMapper studentSignMapper;

    @Override
    public int deleteById(Integer id) {
        return studentSignMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(StudentSign record) {
        record.setCreateDate(getNowDateTime());
        return studentSignMapper.insertSelective(record);
    }

    @Override
    public StudentSign selectById(Integer id) {
        return studentSignMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(StudentSign record) {
        return studentSignMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ArrayList<StudentSign> selectByRequest(StudentSign request) {
        return studentSignMapper.selectByRequest(request);
    }

    @Override
    public int selectCountByRequest(StudentSign request) {
        return studentSignMapper.selectCountByRequest(request);
    }

    @Override
    public int batchInsert(List<StudentSign> studentSignList) {
        return studentSignMapper.batchInsert(studentSignList);
    }

    @Override
    public int updateStatusByClassIdAndWeekAndTeacher(StudentSign record) {
        return studentSignMapper.updateStatusByClassIdAndWeekAndTeacher(record);
    }

    @Override
    public ArrayList<StudentSignInfoToTeacher> selectStudentSignInfoToTeacher(String teacherId, String classId, int week) {
        return studentSignMapper.selectStudentSignInfoToTeacher(teacherId,classId,week);
    }

    @Override
    public ArrayList<StudentSignInfoToStudent> selectStudentSignInfoToStudent(String studentId, String semester, int week, int status) {
        semester="_"+semester+"%";
        ArrayList<StudentSignInfoToStudent> studentSignInfoToStudentList=studentSignMapper.selectStudentSignInfoToStudent(studentId,semester,week,status);
        for (int i = 0; i < studentSignInfoToStudentList.size(); i++) {
            stringToList(studentSignInfoToStudentList.get(i));
        }
        return studentSignInfoToStudentList;
    }

    public Date getNowDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /*
   *将SignInfoToStudent中的classWeekString、weekDaysString、classNumString等字符串属性转换为数组
   */
    public void stringToList(StudentSignInfoToStudent course) {
        Date date=course.getCreateDate();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, 1);
        course.setEndDate(c.getTime());
        List<Integer> classWeek = new ArrayList<Integer>();
        List<Integer> weekDays = new ArrayList<Integer>();
        List<Integer> classNum = new ArrayList<Integer>();
        //classWeekString转换为classWeek数组
        String[] arr = course.getClassWeekString().split(",");
        for (int i = 0; i < arr.length; i++) {
            int m = Integer.parseInt(arr[i]);
            classWeek.add(m);
        }
        //weekDaysString转换为classWeek数组
        String[] arr1 = course.getWeekDaysString().split(",");
        for (int i = 0; i < arr1.length; i++) {
            int m = Integer.parseInt(arr1[i]);
            weekDays.add(m);
        }
        //classNumString转换为classNum数组
        String[] arr2 = course.getClassNumString().split(",");
        for (int i = 0; i < arr2.length; i++) {
            int m = Integer.parseInt(arr2[i]);
            classNum.add(m);
        }
        course.setClassWeek(classWeek);
        course.setWeekDays(weekDays);
        course.setClassNum(classNum);
    }

}
