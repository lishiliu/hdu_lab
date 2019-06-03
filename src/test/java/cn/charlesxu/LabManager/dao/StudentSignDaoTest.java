package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.Class;
import cn.charlesxu.LabManager.entity.StudentSign;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyan on 2018/1/30.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentSignDaoTest extends BaseTest {

    @Autowired
    private StudentSignDao studentSignDao;


    @Test
    public void selectByRequest() {
        StudentSign studentSign=new StudentSign();
        studentSign.setStatus(1);
        studentSignDao.selectByRequest(studentSign);
        System.out.println(" ");
    }

}
