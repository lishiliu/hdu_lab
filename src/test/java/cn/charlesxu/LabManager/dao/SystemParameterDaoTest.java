package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.SystemParameter;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liyan on 2018/2/3.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SystemParameterDaoTest extends BaseTest {

    @Autowired
    private SystemParameterDao systemParameterDao;

    @Test
    public void updateByThisSemesterId() {
        int count = systemParameterDao.updateByThisSemesterId(2);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void updateByNowOrderSemesterId() {
        int count = systemParameterDao.updateByNowOrderSemesterId(2);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void updateByThisWeek() {
        int count = systemParameterDao.updateByThisWeek(2);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void select() {
        SystemParameter systemParameter = systemParameterDao.select();
        Assert.assertTrue(systemParameter != null);
    }
}
