package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by
 * User: Charles Xu
 * Date: 6/2/2018
 * Time: 19:17
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest extends BaseTest {
    @Autowired
    private UserService userService;

    @Test
    public void adminLoginTest() {
        int result = userService.adminLogin("40376", "123456");
        System.out.println(result);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUserName("40392");
        user.setUserNickname("hahha");
        user.setPhone("13588498809");
        user.setEmail("jinjj@hdu.edu.cn");
        int result = userService.updateUser(user);
        System.out.println(result);
    }
}
