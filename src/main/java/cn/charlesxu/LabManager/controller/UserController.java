package cn.charlesxu.LabManager.controller;

import cn.charlesxu.LabManager.entity.User;
import cn.charlesxu.LabManager.entity.form.User1;
import cn.charlesxu.LabManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserByUserName", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getUserByUserName(@RequestBody Map<String, Object> map) {
        User1 user1 = userService.getUserByUserName(map.get("userName").toString());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("User1", user1);
        return modelMap;
    }

    @RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getAllUser() {
        List<User1> user1List = userService.getAllUser();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("user1List", user1List);
        return modelMap;
    }

    //修改一个用户的信息
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateLab(@RequestBody User user) {
        int result = userService.updateUser(user);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (result == 1) {
            modelMap.put("result", "success");
        } else if (result == -1) {
            modelMap.put("result", "The email has already existed!");
        } else if (result == -2) {
            modelMap.put("result", "The phone has already existed!");
        }
        return modelMap;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> login(@RequestBody User user, HttpSession session) {
        int result = userService.login(user.getUserName(), user.getPassword());//调用service层验证登陆
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (result == 1) {
            session.setAttribute("currUser", user);
            modelMap.put("result", 1);
        } else {
            modelMap.put("result", -1);
        }
        return modelMap;
    }


    //实验室管理员登录接口
    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> adminLogin(@RequestBody User user) {
        int result = userService.adminLogin(user.getUserName(), user.getPassword());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (result == 1) {
            modelMap.put("result", 1);
        } else {
            modelMap.put("result", -1);
        }
        return modelMap;
    }

    @RequestMapping(value = "/logout")
    public void logout(HttpSession session, HttpServletResponse response) {
        try {
            session.invalidate();
            response.getWriter().write("{\"result\":\"Logout Success\"}");
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> addUser(@RequestBody User user, HttpServletRequest request) {
        user.setRegIp(userService.getIp2(request));
        int result = userService.addUser(user);//调用service层进行注册
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (result > 0) {
            modelMap.put("result", "success");
        } else if (result == -1) {
            modelMap.put("result", "The username has already existed!");
        } else if (result == -2) {
            modelMap.put("result", "The email has already existed!");
        } else if (result == -3) {
            modelMap.put("result", "The phone has already existed!");
        } else {
            modelMap.put("result", "fail");
        }
        return modelMap;
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updatePassword(@RequestBody Map<String, Object> map) {
        int result = userService.updatePassword(map.get("userName").toString(), map.get("oldPassword").toString(), map.get("newPassword").toString());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (result == 1) {
            modelMap.put("result", "success");
        } else {
            modelMap.put("result", "userName or oldPassWord is wrong!");
        }
        return modelMap;
    }


    @RequestMapping(value = "/getNowUser", method = RequestMethod.GET)//获取当前登陆用户
    public void getNowUser(HttpServletResponse response, HttpSession session) {
        try {
            System.out.println("开始输出");
            User user = (User) session.getAttribute("currUser");
            //System.out.println(user.getUserName());
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().write("{\"userName\":" + "\"" + user.getUserName() + "\"}");
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
