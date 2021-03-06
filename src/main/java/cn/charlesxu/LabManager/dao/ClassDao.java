package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.entity.Class;

import java.util.List;

/**
 * Created by liyan on 2018/1/30.
 */
public interface ClassDao {
    int delete(Integer id);

    int insert(Class record);

    Class selectById(Integer id);

    List<Class> selectByUserName(String userName);

    List<Class> selectByStudentId(String studentId, String semester);

    List<Class> selectByStudentSignClassId(String semester);

    List<Class> selectByUserNameAndSemester(String userName, String semester);

    int updateById(Class record);

    List<Class> selectByRequest(Class request);
}
