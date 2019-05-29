package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.Class;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by liyan on 2018/1/29.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClassMapperTest extends BaseTest {
    @Autowired
    ClassMapper classMapper;

    @Test
    @Transactional
    @Rollback(false)
    public void insert() {
        Class c = new Class();
        c.setClassId("2017-2018-2 A15662");
        c.setClassName("ios");
        c.setClassPeoCount(5);
        c.setUserName("1");
        c.setClassWeekString("123456789");
        c.setWeekDaysString("5");
        c.setClassNumString("12");
        int count = classMapper.insert(c);
        Assert.assertTrue(count == 1);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void delete() {
        Class c = new Class();
        c.setId(11111);
        c.setClassId("2017-2018-2 A15662");
        c.setClassName("ios");
        c.setClassPeoCount(5);
        c.setUserName("1");
        c.setClassWeekString("123456789");
        c.setWeekDaysString("5");
        c.setClassNumString("12");
        int count = classMapper.insert(c);
        int count1 = classMapper.deleteByPrimaryKey(11111);
        Assert.assertTrue(count == 1);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void update() {
        Class c = new Class();
        c.setId(11113);
        c.setClassId("2017-2018-2 A15662");
        c.setClassName("ios");
        c.setClassPeoCount(5);
        c.setUserName("1");
        c.setClassWeekString("123456789");
        c.setWeekDaysString("5");
        c.setClassNumString("12");
        int count = classMapper.insert(c);
        c.setClassWeekString("987654321");
        c.setWeekDaysString("2");
        c.setClassNumString("678");
        int count1 = classMapper.updateByPrimaryKey(c);
        Assert.assertTrue(count == 1);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void selectByUserName() {
        List<Class> classList = classMapper.selectByUserName("1");
        Assert.assertTrue(classList != null);
    }

}
