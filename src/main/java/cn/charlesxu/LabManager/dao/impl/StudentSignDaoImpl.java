package cn.charlesxu.LabManager.dao.impl;

import cn.charlesxu.LabManager.dao.StudentDao;
import cn.charlesxu.LabManager.dao.StudentSignDao;
import cn.charlesxu.LabManager.entity.Student;
import cn.charlesxu.LabManager.entity.StudentSign;
import cn.charlesxu.LabManager.mapper.StudentMapper;
import cn.charlesxu.LabManager.mapper.StudentSignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by liyan on 2019/5/31.
 */
@Repository
public class StudentSignDaoImpl implements StudentSignDao{
    @Autowired
    private StudentSignMapper studentSignMapper;

    @Override
    public int deleteById(Integer id) {
        return studentSignMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(StudentSign record) {
        record.setCreateDate(getNowDateTime());
        return studentSignMapper.insertSelective(record);
    }

    @Override
    public StudentSign selectById(Integer id) {
        return studentSignMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(StudentSign record) {
        return studentSignMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ArrayList<StudentSign> selectByRequest(StudentSign request) {
        return studentSignMapper.selectByRequest(request);
    }

    @Override
    public int batchInsert(List<StudentSign> studentSignList) {
        return studentSignMapper.batchInsert(studentSignList);
    }

    @Override
    public int updateStatusByClassIdAndWeekAndTeacher(StudentSign record) {
        return studentSignMapper.updateStatusByClassIdAndWeekAndTeacher(record);
    }

    public Date getNowDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
}
