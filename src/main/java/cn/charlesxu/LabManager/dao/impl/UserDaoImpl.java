package cn.charlesxu.LabManager.dao.impl;

import cn.charlesxu.LabManager.dao.UserDao;
import cn.charlesxu.LabManager.entity.User;
import cn.charlesxu.LabManager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserMapper userMapper;

    public User selectUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User selectByUsernameAndPassword(String Username, String Password) {
        return userMapper.selectByUsernameAndPassword(Username, Password);
    }

    public User selectByUsername(String Username) {
        return userMapper.selectByUsername(Username);
    }

    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    public int selectCountByUserName(String userName) {
        return userMapper.selectCountByUserName(userName);
    }

    public int selectCountByEmail(String email) {
        return userMapper.selectCountByEmail(email);
    }

    public int selectCountByPhone(String phone) {
        return userMapper.selectCountByPhone(phone);
    }

    public int addUser(User user) {

        return userMapper.insertSelective(user);
    }

    public int update(User user) {
        return userMapper.updateByUserName(user);
    }
}
