package cn.charlesxu.LabManager.dao.impl;

import cn.charlesxu.LabManager.dao.ClassDao;
import cn.charlesxu.LabManager.entity.Class;
import cn.charlesxu.LabManager.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyan on 2018/1/30.
 */
@Repository
public class ClassDaoImpl implements ClassDao {

    @Autowired
    private ClassMapper classMapper;

    public int delete(Integer id) {
        return classMapper.deleteByPrimaryKey(id);
    }

    public int insert(Class course) {
        listToString(course);
        return classMapper.insert(course);
    }

    public Class selectById(Integer id) {
        Class course = classMapper.selectByPrimaryKey(id);
        stringToList(course);
        return course;
    }

    public List<Class> selectByUserName(String userName) {
        List<Class> classList = classMapper.selectByUserName(userName);
        for (int i = 0; i < classList.size(); i++) {
            stringToList(classList.get(i));
        }
        return classList;
    }

    public List<Class> selectByUserNameAndSemester(String userName, String semester) {
        semester = "_" + semester + "%";
        List<Class> classList = classMapper.selectByUserNameAndSemester(userName, semester);
        for (int i = 0; i < classList.size(); i++) {
            stringToList(classList.get(i));
        }
        return classList;
    }

    public List<Class> selectByStudentId(String studentId, String semester) {
        semester = "_" + semester + "%";
        List<Class> classList = classMapper.selectByStudentId(studentId, semester);
        for (int i = 0; i < classList.size(); i++) {
            stringToList(classList.get(i));
        }
        return classList;
    }

    @Override
    public List<Class> selectByStudentSignClassId(String semester) {
        semester = "_" + semester + "%";
        List<Class> classList = classMapper.selectByStudentSignClassId(semester);
        for (int i = 0; i < classList.size(); i++) {
            stringToList(classList.get(i));
        }
        return classList;
    }

    public int updateById(Class course) {
        listToString(course);
        return classMapper.updateByPrimaryKeySelective(course);
    }

    /*
     *将Class中的classWeek、weekDays、classNum等List属性转换为字符串，以存储到数据库
     */
    public void listToString(Class course) {
        String classWeek = new String();
        String weekDays = new String();
        String classNum = new String();
        if (course.getClassWeek() != null) {
            for (int i = 0; i < course.getClassWeek().size(); i++) {
                String m = course.getClassWeek().get(i).toString();
                if (i == 0) {
                    classWeek = m;
                } else {
                    classWeek = classWeek + "," + m;
                }
            }
        }
        if (course.getWeekDays() != null) {
            for (int i = 0; i < course.getWeekDays().size(); i++) {
                String m = course.getWeekDays().get(i).toString();
                if (i == 0) {
                    weekDays = m;
                } else {
                    weekDays = weekDays + "," + m;
                }
            }
        }
        if (course.getClassNum() != null) {
            for (int i = 0; i < course.getClassNum().size(); i++) {
                String m = course.getClassNum().get(i).toString();
                if (i == 0) {
                    classNum = m;
                } else {
                    classNum = classNum + "," + m;
                }
            }
        }
        course.setClassWeekString(classWeek);
        course.setWeekDaysString(weekDays);
        course.setClassNumString(classNum);
    }

    /*
     *将Class中的classWeekString、weekDaysString、classNumString等字符串属性转换为数组
     */
    public void stringToList(Class course) {
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
