package cn.charlesxu.LabManager.controller;

import cn.charlesxu.LabManager.entity.Class;
import cn.charlesxu.LabManager.entity.Semester;
import cn.charlesxu.LabManager.entity.StudentSign;
import cn.charlesxu.LabManager.entity.SystemParameter;
import cn.charlesxu.LabManager.entity.form.Semester1;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToStudent;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToTeacher;
import cn.charlesxu.LabManager.service.SemesterService;
import cn.charlesxu.LabManager.service.StudentSignService;
import cn.charlesxu.LabManager.service.SystemParameterService;
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

    @Autowired
    private SystemParameterService systemParameterService;

    @Autowired
    private SemesterService semesterService;

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
        SystemParameter systemParameter = systemParameterService.getSystemParameter();
        List<StudentSignInfoToTeacher> studentSignInfoToTeacherList=studentSignService.getSignRecordByTeacherAndClassAndWeek(course.getUserName(),course.getClassId(),systemParameter.getThisWeek());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("studentSignInfoToTeacherList", studentSignInfoToTeacherList);
        return modelMap;
    }
    @RequestMapping(value = "/getCurrentSignTask", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getCurrentSignTask(@RequestBody Map<String, Object> map) {
        List<StudentSignInfoToStudent> currentSignTaskList=studentSignService.getCurrentSignTask(map.get("studentId").toString());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("currentSignTask", currentSignTaskList);
        return modelMap;
    }

    @RequestMapping(value = "/getSignRecordToTeacherByWeek", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getSignRecordToTeacherByWeek(@RequestBody Map<String, Object> map) {
        Integer week = new Integer(map.get("selectWeek").toString());
        List<StudentSignInfoToTeacher> studentSignInfoToTeacherList=studentSignService.getSignRecordByTeacherAndClassAndWeek(map.get("teacherId").toString(),map.get("classId").toString(),week);
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
    @RequestMapping(value = "/getSignCountToStudent", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getSignCountToStudent(@RequestBody Map<String, Object> map) {
        SystemParameter systemParameter=systemParameterService.getSystemParameter();
        Semester semester=semesterService.getSemesterById(systemParameter.getThisSemesterId());
        StudentSign request=new StudentSign();
        request.setBeginYear(semester.getBeginYear());
        request.setEndYear(semester.getEndYear());
        request.setTerm(semester.getTerm());
        request.setStatus(2);
        request.setStudentId(map.get("studentId").toString());
        int doCount=studentSignService.selectCountByRequest(request);
        request.setStatus(3);
        int undoCount=studentSignService.selectCountByRequest(request);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("undoCount", undoCount);
        modelMap.put("doCount", doCount);
        return modelMap;
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> addStudent(@RequestBody Map<String, Object> map) {
        int result=studentSignService.addStudent(map.get("teacherId").toString(),map.get("classId").toString(),map.get("studentId").toString(),map.get("studentName").toString());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (result > 0) {
            modelMap.put("result", "success");
        } else {
            modelMap.put("result", "fail");
        }
        return modelMap;
    }

    @RequestMapping(value = "/addStudentSign", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> addStudentSign(@RequestBody Map<String, Object> map) {
        StudentSign studentSign=new StudentSign();
        studentSign.setStudentId(map.get("studentId").toString());
        studentSign.setClassId(map.get("classId").toString());
        studentSign.setComputerNo(map.get("computerNo").toString());
        Integer labId = new Integer(map.get("labId").toString());
        studentSign.setLabId(labId);
        SystemParameter systemParameter=systemParameterService.getSystemParameter();
        studentSign.setWeek(systemParameter.getThisWeek());
        int result=studentSignService.signIn(studentSign);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (result > 0) {
            modelMap.put("result", "success");
        } else {
            modelMap.put("result", "fail");
        }
        return modelMap;
    }
}
