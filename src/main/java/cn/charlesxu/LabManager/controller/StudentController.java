package cn.charlesxu.LabManager.controller;

import cn.charlesxu.LabManager.entity.Student;
import cn.charlesxu.LabManager.entity.User;
import cn.charlesxu.LabManager.entity.form.User1;
import cn.charlesxu.LabManager.service.StudentService;
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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/getStudentByStudentId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getUserByUserName(@RequestBody Map<String, Object> map) {
        Student student = studentService.getStudentByStudentId(map.get("studentId").toString());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("Student", student);
        return modelMap;
    }

    //修改一个用户的信息
    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateStudent(@RequestBody Student student) {
        int result = studentService.updateStudent(student);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (result == 1) {
            modelMap.put("result", "success");
        } else if(result==-1){
            modelMap.put("result", "The studentId has already existed!");
        }
        return modelMap;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> login(@RequestBody Student student, HttpSession session) {
        int result = studentService.login(student.getStudentId(), student.getPassword());//调用service层验证登陆
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (result == 1) {
            session.setAttribute("currUser", student);
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

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updatePassword(@RequestBody Map<String, Object> map) {
        int result = studentService.updatePassword(map.get("userName").toString(), map.get("oldPassword").toString(), map.get("newPassword").toString());
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
            Student student = (Student) session.getAttribute("currUser");
            //System.out.println(user.getUserName());
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().write("{\"userName\":" + "\"" + student.getStudentId() + "\"}");
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
