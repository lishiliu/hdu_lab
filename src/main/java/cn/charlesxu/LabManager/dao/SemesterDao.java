package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.entity.Semester;

/**
 * Created by liyan on 2018/1/31.
 */
public interface SemesterDao {
    int addSemester(Semester semester);

    int delete(Integer id);

    int updateSemester(Semester semester);

    Semester selectById(Integer id);


}
