package cn.charlesxu.LabManager.service.impl;

import cn.charlesxu.LabManager.dao.SemesterDao;
import cn.charlesxu.LabManager.dao.SystemParameterDao;
import cn.charlesxu.LabManager.entity.Semester;
import cn.charlesxu.LabManager.entity.SystemParameter;
import cn.charlesxu.LabManager.entity.form.Semester1;
import cn.charlesxu.LabManager.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyan on 2018/2/1.
 */
@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterDao semesterDao;

    @Autowired
    private SystemParameterDao systemParameterDao;

    public int addSemester(Semester semester) {
        return semesterDao.addSemester(semester);
    }

    public int updateSemester(Semester semester) {
        return semesterDao.updateSemester(semester);
    }

    public int deleteSemester(Integer semesterId) {
        return semesterDao.delete(semesterId);
    }

    public Semester getSemesterById(Integer id) {
        return semesterDao.selectById(id);
    }

    public Semester1 getNowSemester() {
        SystemParameter systemParameter = systemParameterDao.select();
        Semester semester = semesterDao.selectById(systemParameter.getThisSemesterId());
        String nowSemester = semester.getBeginYear().toString() + "-" + semester.getEndYear().toString() + "学年第" +
                semester.getTerm().toString() + "学期(本周为第" + systemParameter.getThisWeek() + "周)";
        List<Integer> week = semester.getWeek();
        int maxWeek = week.get(0);
        for (int i = 1; i < week.size(); i++) {
            if (week.get(i) > maxWeek) {
                maxWeek = week.get(i);
            }
        }
        Semester1 semester_1 = new Semester1();
        semester_1.setNowSemester(nowSemester);
        semester_1.setThisWeek(systemParameter.getThisWeek());
        semester_1.setMaxWeek(maxWeek);
        return semester_1;
    }

    public String getNowSemesterString() {
        SystemParameter systemParameter = systemParameterDao.select();
        Semester semester = getSemesterById(systemParameter.getThisSemesterId());
        String semesterString = semester.getSemesterString();
        return semesterString;
    }
}
