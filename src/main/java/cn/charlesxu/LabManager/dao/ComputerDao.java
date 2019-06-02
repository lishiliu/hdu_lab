package cn.charlesxu.LabManager.dao;


import cn.charlesxu.LabManager.entity.Computer;
import cn.charlesxu.LabManager.entity.QuartzTask;

import java.util.ArrayList;
import java.util.List;


public interface ComputerDao {
    int deleteById(Integer id);

    int insertSelective(Computer record);

    Computer selectById(Integer id);

    int updateById(Computer record);

    ArrayList<Computer> selectByRequest(Computer request);

    /**
     * 批量插入
     *
     * @param computerList
     * @return
     */
    int batchInsert(List<Computer> computerList);
}
