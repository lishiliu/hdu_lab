package cn.charlesxu.LabManager.dao.impl;

import cn.charlesxu.LabManager.dao.QuartzTaskDao;
import cn.charlesxu.LabManager.dao.StudentDao;
import cn.charlesxu.LabManager.entity.QuartzTask;
import cn.charlesxu.LabManager.entity.Student;
import cn.charlesxu.LabManager.mapper.QuartzTaskMapper;
import cn.charlesxu.LabManager.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liyan on 2019/5/31.
 */
@Repository
public class StudentDaoImpl implements StudentDao{
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int deleteById(Integer id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Student record) {
        record.setRegTime(getNowDateTime());
        return studentMapper.insertSelective(record);
    }

    @Override
    public Student selectById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Student record) {
        return studentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ArrayList<Student> selectByRequest(Student request) {
        return studentMapper.selectByRequest(request);
    }

    public Date getNowDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
}
