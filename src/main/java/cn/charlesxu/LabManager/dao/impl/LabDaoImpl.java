package cn.charlesxu.LabManager.dao.impl;

import cn.charlesxu.LabManager.dao.LabDao;
import cn.charlesxu.LabManager.entity.Lab;
import cn.charlesxu.LabManager.mapper.LabMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LabDaoImpl implements LabDao {
    @Autowired
    private LabMapper labMapper;

    public List<String> getAllLabType() {
        return labMapper.selectAllType();
    }

    public List<Lab> selectByType(String type) {
        if (type != null) {
            return labMapper.selectByType(type);
        } else {
            return labMapper.selectAllLab();
        }
    }

    public int addLab(Lab lab) {
        return labMapper.insertSelective(lab);
    }

    public int updateLab(Lab lab) {
        return labMapper.updateByPrimaryKeySelective(lab);
    }

    public int deleteLab(Lab lab) {
        return labMapper.deleteByPrimaryKey(lab.getId());
    }

    public Lab selectLabByLabId(int labId) {
        return labMapper.selectByPrimaryKey(labId);
    }

    public List<Lab> selectAllLab() {
        return labMapper.selectAllLab();
    }

    public List<Lab> selectByAdminUserName(String username) {
        return labMapper.selectByAdminUserName(username);
    }

    public List<Lab> selectByTag(List<Integer> tag) {
        if (tag != null) {
            return labMapper.selectByTag(tag, tag.size());
        } else {
            return labMapper.selectAllLab();
        }
    }

}
