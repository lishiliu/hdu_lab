package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.Lab;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by liyan on 2018/2/3.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LabDaoTest extends BaseTest {

    @Autowired
    private LabDao labDao;

    @Test
    public void selectAllLab() {
        List<Lab> labList = labDao.selectAllLab();
        Assert.assertTrue(labList != null);
    }

    @Test
    public void selectLabByAdminUserName() {
        List<Lab> labList = labDao.selectByAdminUserName("40376");
        Assert.assertTrue(labList != null);
    }
}
