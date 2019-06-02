package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.entity.StudentSign;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public interface StudentSignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentSign record);

    int insertSelective(StudentSign record);

    StudentSign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentSign record);

    int updateByPrimaryKey(StudentSign record);

    ArrayList<StudentSign> selectByRequest(StudentSign request);


    /**
     * 批量插入
     *
     * @param studentSignList
     * @return
     */
    int batchInsert(List<StudentSign> studentSignList);
}