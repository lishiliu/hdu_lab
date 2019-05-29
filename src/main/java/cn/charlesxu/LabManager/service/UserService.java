package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.entity.User;
import cn.charlesxu.LabManager.entity.form.User1;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    User1 getUserByUserName(String userName);

    User1 selectUserById(long id);

    String selectUsernameById(int id);

    int login(String username, String password);

    int adminLogin(String username, String password);

    int addUser(User user);

    String getNicknameByUsername(String username);

    int updatePassword(String userName, String oldPassword, String newPassword);

    int updateUser(User user);

    List<User1> getAllUser();

    String getIp2(HttpServletRequest request);
}
