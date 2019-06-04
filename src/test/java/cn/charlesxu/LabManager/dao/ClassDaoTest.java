package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.Class;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.internal.requests.ClassRequest;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyan on 2018/1/30.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClassDaoTest extends BaseTest {

    @Autowired
    private ClassDao classDao;

    @Test
    public void insert() {
        Class c = new Class();
        c.setClassId("2017-2018-2 A15662");
        c.setClassName("ios");
        c.setClassPeoCount(5);
        c.setUserName("1");
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
        int count = classDao.insert(c);

    }

    @Test
    public void selectByUserName() {
        List<Class> classList = classDao.selectByUserName("1");
        Assert.assertTrue(classList != null);
        for (int j = 0; j < classList.size(); j++) {
            Class cc = classList.get(j);
            for (int i = 0; i < cc.getClassWeek().size(); i++)
                System.out.print(cc.getClassWeek().get(i));
            System.out.print(" ");
            for (int i = 0; i < cc.getWeekDays().size(); i++)
                System.out.print(cc.getWeekDays().get(i));
            System.out.print(" ");
            for (int i = 0; i < cc.getClassNum().size(); i++)
                System.out.print(cc.getClassNum().get(i));
        }
        System.out.println(" ");
    }


    @Test
    public void selectByUserNameAndSemester() {
        List<Class> classList = classDao.selectByUserNameAndSemester("40387", "2017-2018-2");
        Assert.assertTrue(classList != null);

    }

    @Test
    public void selectByRequest() {
        Class request=new Class();
        request.setClassId("(2017-2018-2)-B0507110-40392-2");
        List<Class> classList = classDao.selectByRequest(request);
        Assert.assertTrue(classList != null);

    }

    @Test
    public void update() {
        Class c = new Class();
        c.setId(1);
        c.setClassPeoCount(41);
        List<Integer> classWeek = new ArrayList<Integer>();
        for (int i = 1; i < 16; i++) {
            classWeek.add(i);
        }
        c.setClassWeek(classWeek);
        List<Integer> weekDays = new ArrayList<Integer>();
        weekDays.add(4);
        c.setWeekDays(weekDays);
        List<Integer> classNum = new ArrayList<Integer>();
        classNum.add(10);
        classNum.add(11);
        classNum.add(12);
        c.setClassNum(classNum);
        int count = classDao.updateById(c);
        Assert.assertTrue(count == 1);
    }
}
