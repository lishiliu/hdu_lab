package cn.charlesxu.LabManager.dao;


import cn.charlesxu.LabManager.entity.Computer;
import cn.charlesxu.LabManager.entity.QuartzTask;

import java.util.ArrayList;


public interface ComputerDao {
    int deleteById(Integer id);

    int insertSelective(Computer record);

    Computer selectById(Integer id);

    int updateById(Computer record);

    ArrayList<Computer> selectByRequest(Computer request);
}
