package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.entity.Lab;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LabMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Lab record);

    int insertSelective(Lab record);

    Lab selectByPrimaryKey(Integer id);

    List<Lab> selectByAdminUserName(@Param("username") String username);

    List<Lab> selectByType(@Param("type") String type);

    List<Lab> selectByTag(@Param("tag") List<Integer>tag,@Param("tagSize")Integer tagSize);

    List<String> selectAllType();

    List<Lab> selectAllLab();

    int updateByPrimaryKeySelective(Lab record);

    int updateByPrimaryKey(Lab record);
}