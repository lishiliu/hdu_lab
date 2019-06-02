package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.entity.Class;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Class course);

    int insertSelective(Class course);

    Class selectByPrimaryKey(Integer id);

    List<Class> selectByUserName(String userName);

    List<Class> selectByStudentId(@Param("studentId") String studentId, @Param("semester") String semester);

    List<Class> selectByStudentSignClassId(@Param("semester")String semester);

    List<Class> selectByUserNameAndSemester(@Param("userName") String userName, @Param("semester") String semester);

    int updateByPrimaryKeySelective(Class course);

    int updateByPrimaryKey(Class course);
}