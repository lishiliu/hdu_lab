package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.form.Semester1;
import cn.charlesxu.LabManager.service.Job.SetWeekToZERO;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by
 * User: Charles Xu
 * Date: 3/2/2018
 * Time: 21:37
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SystemParameterServiceTest extends BaseTest {

    @Autowired
    private SystemParameterService systemParameterService;

    @Autowired
    private SemesterService semesterService;

    @Test
    public void test() {
        SetWeekToZERO setWeekToZERO = new SetWeekToZERO();
        systemParameterService.setSemester(0);
    }

    @Test
    public void semesterTest() {
        Semester1 semester = semesterService.getNowSemester();
        Assert.assertTrue(semester != null);
    }

}
