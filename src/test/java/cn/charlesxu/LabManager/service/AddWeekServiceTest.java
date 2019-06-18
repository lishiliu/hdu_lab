package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.Class;
import cn.charlesxu.LabManager.service.Job.AddWeek;
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

    @Test
    public void selectClassTest() {
        addWeek.job1();
    }
}
