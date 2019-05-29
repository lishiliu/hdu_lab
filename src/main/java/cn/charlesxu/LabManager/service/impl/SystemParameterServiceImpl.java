package cn.charlesxu.LabManager.service.impl;

import cn.charlesxu.LabManager.dao.SystemParameterDao;
import cn.charlesxu.LabManager.entity.Semester;
import cn.charlesxu.LabManager.entity.SystemParameter;
import cn.charlesxu.LabManager.service.Job.SetWeekToZERO;
import cn.charlesxu.LabManager.service.Quartz.QuartzManager;
import cn.charlesxu.LabManager.service.SemesterService;
import cn.charlesxu.LabManager.service.SystemParameterService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by
 * User: Charles Xu
 * Date: 3/2/2018
 * Time: 19:27
 */
@Service
public class SystemParameterServiceImpl implements SystemParameterService {
    @Autowired
    private SemesterService semesterService;

    @Autowired
    private SystemParameterDao systemParameterDao;


    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private QuartzManager quartzManager;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    private ScheduledFuture<?> future;

    public int setSemester(int semesterId) {
        //调用Dao层修改数据库表中的系统参数
        int result = systemParameterDao.updateByThisSemesterId(semesterId);

        if (result > 0) {
            //定时将week的值初始化
            Semester semester = semesterService.getSemesterById(semesterId);
            Date beginDate = semester.getBeginDate();
            DateTime dateTime = new DateTime(beginDate);
            int year = dateTime.getYear();
            int month = dateTime.getMonthOfYear();
            int day = dateTime.getDayOfMonth();
            day -= 1;

            //学期开始前十分钟，将Week赋值为0
            String CRON = "0 50 23 " + day + " " + month + " " + "? " + year + "-" + year;
            //System.out.println(CRON);

            String JOB_NAME = "Week初始化";
            String JOB_GROUP_NAME = "WeekToZERO_JOB_GROUP";

            quartzManager.addJob(SetWeekToZERO.class, JOB_NAME, JOB_GROUP_NAME, CRON);

        }

        return result;
    }

    public SystemParameter getSystemParameter() {
        return systemParameterDao.select();
    }

}
