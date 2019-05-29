package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.entity.Semester;
import cn.charlesxu.LabManager.entity.form.Semester1;

/**
 * Created by liyan on 2018/2/1.
 */
public interface SemesterService {

    int addSemester(Semester semester);

    int updateSemester(Semester semester);

    int deleteSemester(Integer semesterId);

    Semester getSemesterById(Integer id);

    Semester1 getNowSemester();

    String getNowSemesterString();
}
