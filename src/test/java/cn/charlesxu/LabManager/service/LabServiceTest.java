package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.dao.LabDao;
import cn.charlesxu.LabManager.entity.Lab;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * User: Charles Xu
 * Date: 12/8/2018
 * Time: 16:28
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LabServiceTest extends BaseTest {
    @Autowired
    private LabService labService;

    @Autowired
    private LabDao labDao;

    @Test
    public void run(){
//        List<Lab> labList = labDao.selectAllLab();
//        for(Lab lab : labList){
//            labService.calculateLabUsage(lab.getId(),"2013-2014-2");
//        }

        List<Integer> tag = new ArrayList<Integer>();
        tag.add(1);
        //tag.add(2);
        List<Lab> labList = labDao.selectByTag(tag);
        //System.out.println(labList);
    }
}
