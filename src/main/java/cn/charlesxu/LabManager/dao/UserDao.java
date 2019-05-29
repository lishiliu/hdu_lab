package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.entity.User;

import java.util.List;

public interface UserDao {
    User selectUserById(int id);

    User selectByUsernameAndPassword(String Username, String Password);

    User selectByUsername(String Username);

    List<User> selectAllUser();

    int selectCountByUserName(String userName);

    int selectCountByEmail(String email);

    int selectCountByPhone(String phone);

    int addUser(User user);

    int update(User user);
}
