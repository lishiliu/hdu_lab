package cn.charlesxu.LabManager.dao.impl;

import cn.charlesxu.LabManager.dao.ComputerDao;
import cn.charlesxu.LabManager.dao.StudentDao;
import cn.charlesxu.LabManager.entity.Computer;
import cn.charlesxu.LabManager.entity.Student;
import cn.charlesxu.LabManager.mapper.ComputerMapper;
import cn.charlesxu.LabManager.mapper.StudentMapper;
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
public class ComputerDaoImpl implements ComputerDao{
    @Autowired
    private ComputerMapper computerMapper;

    @Override
    public int deleteById(Integer id) {
        return computerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Computer record) {
        record.setStatus(0);
        record.setCreateTime(getNowDateTime());
        record.setUpdateTime(getNowDateTime());
        return computerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Computer selectById(Integer id) {
        return computerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Computer record) {
        record.setUpdateTime(getNowDateTime());
        return computerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ArrayList<Computer> selectByRequest(Computer request) {
        return computerMapper.selectByRequest(request);
    }

    @Override
    public int selectCountByRequest(Computer request) {
        return computerMapper.selectCountByRequest(request);
    }

    @Override
    public int batchInsert(List<Computer> computerList) {
        return computerMapper.batchInsert(computerList);
    }

    public Date getNowDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

}
