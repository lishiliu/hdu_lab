package cn.charlesxu.LabManager.dao.impl;

import cn.charlesxu.LabManager.dao.SemesterDao;
import cn.charlesxu.LabManager.entity.Semester;
import cn.charlesxu.LabManager.mapper.SemesterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyan on 2018/1/31.
 */
@Repository
public class SemesterDaoImpl implements SemesterDao {

    @Autowired
    private SemesterMapper semesterMapper;


    public int addSemester(Semester semester) {
        listToString(semester);
        int count = semesterMapper.insert(semester);
        return count;
    }

    public int delete(Integer id) {
        return semesterMapper.deleteByPrimaryKey(id);
    }

    public int updateSemester(Semester semester) {
        listToString(semester);
        return semesterMapper.updateByPrimaryKeySelective(semester);
    }

    public Semester selectById(Integer id) {
        Semester semester = semesterMapper.selectByPrimaryKey(id);
        stringToList(semester);
        return semester;
    }

    /*
     *将Semester中的week、classNum等List属性转换为字符串，以存储到数据库
     */
    public void listToString(Semester semester) {
        String week = new String();
        String classNum = new String();
        if (semester.getWeek() != null) {
            for (int i = 0; i < semester.getWeek().size(); i++) {
                String m = semester.getWeek().get(i).toString();
                if (i == 0) {
                    week = m;
                } else {
                    week = week + "," + m;
                }
            }
        }

        if (semester.getClassNum() != null) {
            for (int i = 0; i < semester.getClassNum().size(); i++) {
                String m = semester.getClassNum().get(i).toString();
                if (i == 0) {
                    classNum = m;
                } else {
                    classNum = classNum + "," + m;
                }
            }
        }
        semester.setWeekString(week);
        semester.setClassNumString(classNum);
    }

    /*
     *将Semester中的weekString、classNumString等字符串属性转换为数组
     */
    public void stringToList(Semester semester) {
        List<Integer> week = new ArrayList<Integer>();
        List<Integer> classNum = new ArrayList<Integer>();
        //weekString转换为week数组
        String[] arr = semester.getWeekString().split(",");
        for (int i = 0; i < arr.length; i++) {
            int m = Integer.parseInt(arr[i]);
            week.add(m);
        }
        //classNumString转换为classNum数组
        String[] arr1 = semester.getClassNumString().split(",");
        for (int i = 0; i < arr1.length; i++) {
            int m = Integer.parseInt(arr1[i]);
            classNum.add(m);
        }
        semester.setWeek(week);
        semester.setClassNum(classNum);
    }

}
