package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.entity.Lab;

import java.util.List;


public interface LabDao {
    List<String> getAllLabType();

    int addLab(Lab lab);

    int updateLab(Lab lab);

    int deleteLab(Lab lab);

    List<Lab> selectByType(String type);

    Lab selectLabByLabId(int labId);

    List<Lab> selectAllLab();

    List<Lab> selectByAdminUserName(String username);

    List<Lab> selectByTag(List<Integer> tag);
}
