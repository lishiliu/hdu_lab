package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liyan on 2018/2/1.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void selectCountByUserName() {
        int count = userDao.selectCountByUserName("2");
        int count1 = userDao.selectCountByUserName("5");
        System.out.println(count + " " + count1);
    }

    @Test
    public void selectCountByPhone() {
        int count = userDao.selectCountByPhone("13100170001");
        int count1 = userDao.selectCountByPhone("5");
        System.out.println(count + " " + count1);
    }

    @Test
    public void selectCountByEmail() {
        int count = userDao.selectCountByEmail("123456@qq.com");
        int count1 = userDao.selectCountByEmail("5");
        System.out.println(count + " " + count1);
    }

    @Test
    public void updateByUserName() {
        User user = new User();
        user.setUserName("40376");
        user.setEmail("699999777@qq.com");
        user.setPhone("18667139766");

        int count = userDao.update(user);

    }
}
