package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.Class;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyan on 2018/2/26.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClassServiceTest extends BaseTest {
    @Autowired
    private ClassService classService;

    @Test
    public void addClassTest() {
        Class c = new Class();
        c.setClassId("2017-");
        c.setClassName("5550");
        c.setClassPeoCount(5);
        c.setUserName("5555");
        List<Integer> classWeek = new ArrayList<Integer>();
        for (int i = 1; i < 16; i++) {
            classWeek.add(i);
        }
        c.setClassWeek(classWeek);
        List<Integer> weekDays = new ArrayList<Integer>();
        weekDays.add(5);
        c.setWeekDays(weekDays);
        List<Integer> classNum = new ArrayList<Integer>();
        classNum.add(10);
        classNum.add(11);
        classNum.add(12);
        c.setClassNum(classNum);
        int result = classService.addClass(c);
        System.out.println(c.getClassWeekString());
        System.out.println(c.getWeekDaysString());
        System.out.println(c.getClassNumString());
    }

    @Test
    public void selectClassTest() {
        List<Class> classList = classService.selectClassByUsername("40392");
        Assert.assertTrue(classList != null);
    }
}
