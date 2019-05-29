package cn.charlesxu.LabManager.service.impl;

import cn.charlesxu.LabManager.dao.ClassDao;
import cn.charlesxu.LabManager.dao.SystemParameterDao;
import cn.charlesxu.LabManager.entity.Class;
import cn.charlesxu.LabManager.entity.Semester;
import cn.charlesxu.LabManager.entity.SystemParameter;
import cn.charlesxu.LabManager.service.ClassService;
import cn.charlesxu.LabManager.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyan on 2018/1/30.
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassDao classDao;

    @Autowired
    private SystemParameterDao systemParameterDao;

    @Autowired
    private SemesterService semesterService;

    public int addClass(Class course) {
        return classDao.insert(course);
    }

    public int updateClass(Class course) {
        return classDao.updateById(course);
    }

    public int deleteClass(Class course) {
        return classDao.delete(course.getId());
    }

    public List<Class> selectClassByUsername(String UserName) {
        SystemParameter systemParameter = systemParameterDao.select();
        Semester semester = semesterService.getSemesterById(systemParameter.getThisSemesterId());
        return classDao.selectByUserNameAndSemester(UserName, semester.getSemesterString());
    }

    public List<Class> selectClassByUsernameAndSemester(String UserName, String semester) {
        return classDao.selectByUserNameAndSemester(UserName, semester);
    }
}
