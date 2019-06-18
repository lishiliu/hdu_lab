package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.Computer;
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
public class ComputerDaoTest extends BaseTest {

    @Autowired
    private ComputerDao computerDao;

    @Test
    public void selectComputer() {
        Computer request=new Computer();
        request.setLabId(5);
        request.setStatus(0);
        List<Computer> computerList = computerDao.selectByRequest(request);
        Assert.assertTrue(computerList != null);
    }


}
