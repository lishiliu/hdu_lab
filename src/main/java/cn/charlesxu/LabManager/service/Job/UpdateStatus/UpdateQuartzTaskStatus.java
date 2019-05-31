package cn.charlesxu.LabManager.service.Job.UpdateStatus;

import cn.charlesxu.LabManager.dao.QuartzTaskDao;
import cn.charlesxu.LabManager.entity.QuartzTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liyan on 2019/5/31.
 */
@Component
public class UpdateQuartzTaskStatus {
    @Autowired
    QuartzTaskDao quartzTaskDao;
    public void updateQuartzTaskStatus(String jobName,String jobGroupName){
        QuartzTask quartzTask=new QuartzTask();
        quartzTask.setJobName(jobName);
        quartzTask.setJobGroupName(jobGroupName);
        List<QuartzTask> quartzTasks=quartzTaskDao.selectSelective(quartzTask);
        quartzTask=quartzTasks.get(0);
        quartzTask.setStatus(1);
        quartzTaskDao.updateById(quartzTask);
    }
}
