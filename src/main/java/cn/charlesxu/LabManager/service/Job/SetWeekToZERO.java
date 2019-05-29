package cn.charlesxu.LabManager.service.Job;

import cn.charlesxu.LabManager.dao.SystemParameterDao;
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
public class SetWeekToZERO implements Job {
    @Autowired
    private SystemParameterDao systemParamterDao;

    public void execute(JobExecutionContext jobExecutionContext) {
        systemParamterDao.updateByThisWeek(0);
    }
}
