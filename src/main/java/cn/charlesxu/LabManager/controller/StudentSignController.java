package cn.charlesxu.LabManager.controller;

import cn.charlesxu.LabManager.entity.Class;
import cn.charlesxu.LabManager.entity.StudentSign;
import cn.charlesxu.LabManager.entity.form.Semester1;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToTeacher;
import cn.charlesxu.LabManager.service.SemesterService;
import cn.charlesxu.LabManager.service.StudentSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyan on 2018/3/9.
 */
@Controller
@CrossOrigin(maxAge = 3600)
@RequestMapping("/studentSignIn")
public class StudentSignController {
    @Autowired
    private StudentSignService studentSignService;

    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateStatus(@RequestBody Class course) {
        int result =studentSignService.updateStatus(course.getUserName(),course.getClassId(),1);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (result > 0) {
            modelMap.put("result", result);
        } else {
            modelMap.put("result", -1);
        }

        return modelMap;
    }

    @RequestMapping(value = "/getSignRecordToTeacher", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getSignRecordToTeacher(@RequestBody Class course) {
        List<StudentSignInfoToTeacher> studentSignInfoToTeacherList=studentSignService.getSignRecordByTeacherAndClassAndWeek(course.getUserName(),course.getClassId());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("studentSignInfoToTeacherList", studentSignInfoToTeacherList);
        return modelMap;
    }

    @RequestMapping(value = "/getSignCountToTeacher", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getSignCountToTeacher(@RequestBody Class course) {
        StudentSign request=new StudentSign();
        request.setStatus(1);
        request.setClassId(course.getClassId());
        request.setTeacherId(course.getUserName());
        int undoCount1=studentSignService.selectCountByRequest(request);
        request.setStatus(3);
        int undoCount2=studentSignService.selectCountByRequest(request);
        int undoCount=undoCount1+undoCount2;
        request.setStatus(2);
        int doCount=studentSignService.selectCountByRequest(request);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("undoCount", undoCount);
        modelMap.put("doCount", doCount);
        return modelMap;
    }
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> addStudent(@RequestBody Map<String, Object> map) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        return modelMap;
    }
}
