package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.entity.StudentSign;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;


public interface StudentSignDao {
    int deleteById(Integer id);

    int insertSelective(StudentSign record);

    StudentSign selectById(Integer id);

    int updateById(StudentSign record);

    ArrayList<StudentSign> selectByRequest(StudentSign request);

    int selectCountByRequest(StudentSign request);
    /**
     * 批量插入
     *
     * @param studentSignList
     * @return
     */
    int batchInsert(List<StudentSign> studentSignList);

    int updateStatusByClassIdAndWeekAndTeacher(StudentSign record);

    ArrayList<StudentSignInfoToTeacher> selectStudentSignInfoToTeacher(@Param("teacherId") String teacherId, @Param("classId") String classId, @Param("week") int week);

}
