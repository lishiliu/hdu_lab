package cn.charlesxu.LabManager.service.Quartz;

import cn.charlesxu.LabManager.dao.QuartzTaskDao;
import cn.charlesxu.LabManager.entity.QuartzTask;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by liyan on 2019/5/31.
 */
@Service
public class QuartzTaskStart implements InitializingBean {
    @Autowired
    QuartzTaskDao quartzTaskDao;

    @Autowired
    QuartzManager quartzManager;


    public void afterPropertiesSet() throws Exception {
        System.out.println("定时器初始化");
        //获取未执行的任务
       /* QuartzTask quartzTask=new QuartzTask();
        quartzTask.setStatus(0);
        List<QuartzTask> quartzTasks= quartzTaskDao.selectSelective(quartzTask);
        if(quartzTasks!=null&&quartzTasks.size()>0){
            for(QuartzTask quartzTask1:quartzTasks){
                Class obj=Class.forName(quartzTask1.getClassName());
                //重启未过时的任务
                if(getNowDateTime().before(quartzTask1.getEndDate())){
                    quartzManager.addJob(obj,quartzTask1.getJobName(),quartzTask1.getJobGroupName(),quartzTask1.getCron());
                }
               *//* //过时的任务删除
                if(getNowDateTime().after(quartzTask1.getEndDate())){
                    quartzTaskMapper.deleteByPrimaryKey(quartzTask1.getId());
                }*//*
                //过时的未执行任务重新加一天定时
                if(getNowDateTime().after(quartzTask1.getEndDate())){
                    String CRON=dateToCron(getNowDateTime());
                    quartzManager.addJob(obj,quartzTask1.getJobName(),quartzTask1.getJobGroupName(),CRON);
                    Calendar c = Calendar.getInstance();
                    Date date=getNowDateTime();
                    c.setTime(date);
                    c.add(Calendar.DAY_OF_MONTH, 1);
                    quartzTask1.setEndDate(c.getTime());
                    quartzTask1.setUpdateTime(getNowDateTime());
                    quartzTask1.setCron(CRON);
                    quartzTaskDao.updateStatusByJob(quartzTask1);
                }
            }

        }*/
        System.out.println("定时器初始化成功");
    }


    public Date getNowDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
    public String dateToCron(Date date){
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);//获取年份
        int month=cal.get(Calendar.MONTH)+1;//获取月份
        int day=cal.get(Calendar.DATE)+1;//获取日
        int hour=cal.get(Calendar.HOUR_OF_DAY);//小时
        int minute=cal.get(Calendar.MINUTE);//分
        String CRON = "0"+ " "+minute+" "+hour+" " + day + " " + month + " " +"? "+ year;
        return  CRON;
    }


}
