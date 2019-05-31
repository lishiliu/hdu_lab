package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.entity.Computer;
import org.springframework.stereotype.Component;

@Component
public interface ComputerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Computer record);

    int insertSelective(Computer record);

    Computer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Computer record);

    int updateByPrimaryKey(Computer record);
}