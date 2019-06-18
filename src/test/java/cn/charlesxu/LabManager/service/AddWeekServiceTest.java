package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.Class;
import cn.charlesxu.LabManager.service.Job.AddWeek;
import cn.charlesxu.LabManager.service.Job.UpdateStatus.UpdateQuartzTaskStatus;
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
public class AddWeekServiceTest extends BaseTest {
    @Autowired
    private AddWeek addWeek;

    @Autowired
    private UpdateQuartzTaskStatus updateQuartzTaskStatus;

    @Test
    public void selectClassTest() {
        addWeek.job1();
    }
    @Test
    public void setUpdateQuartzTaskStatus() {
        updateQuartzTaskStatus.updateQuartzTaskStatus("40387:Status修改:Wed Jun 19 03:25:51 CST 2019","UpdateSignStatus_JOB_GROUP");
    }
}
