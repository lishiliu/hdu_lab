package cn.charlesxu.LabManager.service.Job;

import cn.charlesxu.LabManager.dao.StudentSignDao;
import cn.charlesxu.LabManager.dao.SystemParameterDao;
import cn.charlesxu.LabManager.entity.StudentSign;
import cn.charlesxu.LabManager.service.Job.UpdateStatus.UpdateQuartzTaskStatus;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by
 * User: Charles Xu
 * Date: 3/2/2018
 * Time: 21:31
 */
@Component
public class EndSign implements Job {
    @Autowired
    private SystemParameterDao systemParamterDao;

    @Autowired
    UpdateQuartzTaskStatus updateQuartzTaskStatus;

    @Autowired
    StudentSignDao studentSignDao;

    public void execute(JobExecutionContext jobExecutionContext) {
        StudentSign studentSign= (StudentSign) jobExecutionContext.getMergedJobDataMap().get("studentSign");
        String jobName=(String)jobExecutionContext.getMergedJobDataMap().get("jobName");
        String jobGroupName=(String)jobExecutionContext.getMergedJobDataMap().get("jobGroupName");
        studentSign.setStatus(3);
        studentSignDao.updateStatusByClassIdAndWeekAndTeacher(studentSign);
        updateQuartzTaskStatus.updateQuartzTaskStatus(jobName,jobGroupName);
        System.out.println("调度成功！");
    }
}
