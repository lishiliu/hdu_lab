package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.Semester;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liyan on 2018/3/2.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SemesterDaoTest extends BaseTest {

    @Autowired
    private SemesterDao semesterDao;

    @Test
    public void addSemesterTest() {
        Semester semester = new Semester();
        semester.setBeginYear(2019);
        semester.setEndYear(2020);
        semester.setTerm(2);
        Date date = new Date();
        semester.setBeginDate(date);
        semester.setEndDate(date);
        List<Integer> week = new ArrayList<Integer>();
        for (int i = 1; i < 16; i++) {
            week.add(i);
        }
        semester.setWeek(week);
        List<Integer> classNum = new ArrayList<Integer>();
        classNum.add(10);
        classNum.add(11);
        classNum.add(12);
        semester.setClassNum(classNum);
        int count = semesterDao.addSemester(semester);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void updateSemesterTest() {
        Semester semester = new Semester();
        semester.setId(3);
        semester.setBeginYear(2018);
        semester.setEndYear(2019);
        semester.setTerm(1);
        Date date = new Date();
        semester.setBeginDate(date);
        semester.setEndDate(date);
        List<Integer> week = new ArrayList<Integer>();
        for (int i = 1; i < 17; i++) {
            week.add(i);
        }
        semester.setWeek(week);
        List<Integer> classNum = new ArrayList<Integer>();
        classNum.add(9);
        classNum.add(10);
        classNum.add(11);
        classNum.add(12);
        semester.setClassNum(classNum);
        int count = semesterDao.updateSemester(semester);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void deleteSemesterTest() {
        int count = semesterDao.delete(3);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void selectSemesterTest() {
        Semester semester = semesterDao.selectById(3);
        Assert.assertTrue(semester != null);
    }
}
