package cn.charlesxu.LabManager.service.Job;

import cn.charlesxu.LabManager.dao.SystemParameterDao;
import cn.charlesxu.LabManager.entity.SystemParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Charles Xu
 * Date: 2018/3/24
 * Time: 23:23
 */
@Component("addWeekJob")
public class AddWeek {
    @Autowired
    private SystemParameterDao systemParamterDao;

    public void job1() {
        SystemParameter systemParameter = systemParamterDao.select();
        systemParamterDao.updateByThisWeek(systemParameter.getThisWeek() + 1);
    }
}
