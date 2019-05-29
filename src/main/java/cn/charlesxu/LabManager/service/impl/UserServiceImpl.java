package cn.charlesxu.LabManager.service.impl;

import cn.charlesxu.LabManager.dao.UserDao;
import cn.charlesxu.LabManager.entity.User;
import cn.charlesxu.LabManager.entity.form.User1;
import cn.charlesxu.LabManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static cn.charlesxu.LabManager.entity.define.AuthDefine.LabAdminAuth;
import static cn.charlesxu.LabManager.entity.define.AuthDefine.TeacherAuth;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User1 getUserByUserName(String userName) {
        User user = userDao.selectByUsername(userName);
        return userToUser1(user);
    }

    public User1 selectUserById(long id) {
        User user = userDao.selectUserById((int) id);
        return userToUser1(user);
    }

    public String selectUsernameById(int id) {
        String userName = selectUserById(id).getUserName();
        if (userName != null) {
            return userName;
        } else {
            return "Not Found";
        }
    }

    public int login(String username, String password) {
        User user = userDao.selectByUsernameAndPassword(username, password);
        if (user != null && user.getUserRole().equals(TeacherAuth)) {
            return 1;
        } else {
            return -1;
        }
    }

    public int adminLogin(String username, String password) {
        User user = userDao.selectByUsernameAndPassword(username, password);
        if (user != null && user.getUserRole().equals(LabAdminAuth)) {
            return 1;
        } else {
            return -1;
        }
    }

    public int addUser(User user) {
        user.setRegTime(getNowDateTime());
        user.setUserRole(TeacherAuth);
        int result = 0;
        if (userDao.selectCountByUserName(user.getUserName()) != 0) {
            result = -1;
        }
        if (result == 0 && userDao.selectCountByEmail(user.getEmail()) != 0) {
            result = -2;
        }
        if (result == 0 && userDao.selectCountByPhone(user.getPhone()) != 0) {
            result = -3;
        }
        if (result == 0) {
            result = userDao.addUser(user);
        }
        return result;
    }

    public int updatePassword(String userName, String oldPassword, String newPassword) {
        User res = userDao.selectByUsernameAndPassword(userName, oldPassword);
        if (res != null) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(newPassword);
            return userDao.update(user);
        } else {
            return -1;
        }
    }

    public int updateUser(User user) {
        int result = 0;
        User user1 = userDao.selectByUsername(user.getUserName());
        if (userDao.selectCountByEmail(user.getEmail()) != 0 && !user.getEmail().equals(user1.getEmail())) {
            result = -1;
        }
        if (userDao.selectCountByPhone(user.getPhone()) != 0 && !user.getPhone().equals(user1.getPhone())) {
            result = -2;
        }

        if (result == 0) {
            result = userDao.update(user);
        }
        return result;
    }

    public List<User1> getAllUser() {
        List<User> userList = userDao.selectAllUser();
        List<User1> user1List = new ArrayList<User1>();
        for (User user : userList) {
            user1List.add(userToUser1(user));
        }
        return user1List;
    }


    public String getNicknameByUsername(String username) {
        return userDao.selectByUsername(username).getUserNickname();
    }

    public User1 userToUser1(User user) {
        User1 user1 = new User1();
        user1.setEmail(user.getEmail());
        user1.setUserName(user.getUserName());
        user1.setUserRole(user.getUserRole());
        user1.setStatus(user.getStatus());
        user1.setRegTime(user.getRegTime());
        user1.setRegIp(user.getRegIp());
        user1.setUserNickname(user.getUserNickname());
        user1.setPhone(user.getPhone());
        return user1;
    }

    public Date getNowDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public String getIp2(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (ip != null && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
