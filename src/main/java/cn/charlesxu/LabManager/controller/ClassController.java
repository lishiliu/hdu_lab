package cn.charlesxu.LabManager.controller;

import cn.charlesxu.LabManager.entity.Class;
import cn.charlesxu.LabManager.entity.CourseTable;
import cn.charlesxu.LabManager.entity.Semester;
import cn.charlesxu.LabManager.entity.SystemParameter;
import cn.charlesxu.LabManager.service.ClassService;
import cn.charlesxu.LabManager.service.CourseTableService;
import cn.charlesxu.LabManager.service.SemesterService;
import cn.charlesxu.LabManager.service.SystemParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by
 * User: Charles Xu
 * Date: 31/1/2018
 * Time: 15:32
 */
@Controller
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = "/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @Autowired
    private CourseTableService courseTableService;

    @Autowired
    private SystemParameterService systemParameterService;

    @Autowired
    private SemesterService semesterService;

    //添加一节课
    @RequestMapping(value = "/addclass", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> addClass(@RequestBody Class course) {
        int result = classService.addClass(course);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", result);
        return modelMap;
    }

    //删除一节课
    @RequestMapping(value = "/deleteclass", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> deleteClass(@RequestBody Class course) {
        int result = classService.deleteClass(course);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", result);
        return modelMap;
    }

    //修改一节课
    @RequestMapping(value = "/updateclass", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateClass(@RequestBody Class course) {
        int result = classService.updateClass(course);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", result);
        return modelMap;
    }

    //根据教师username查询对应的课程
    @RequestMapping(value = "/getclassbyusername", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getClassByUsername(@RequestBody Map<String, Object> map) {
        List<Class> classList1 = classService.selectClassByUsername(
                map.get("userName").toString()
        );

        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("course", classList1);
        return modelMap;
    }

    @RequestMapping(value = "/semester/getclassbyusername", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getClassByUsernameAndSemester(@RequestBody Map<String, Object> map) {
        List<Class> classList1 = classService.selectClassByUsernameAndSemester(
                map.get("userName").toString(),
                map.get("semester").toString()
        );

        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("course", classList1);
        return modelMap;
    }
    @RequestMapping(value = "/semester/getClassBySemesterAndWeek", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getClassBySemesterAndWeek(@RequestBody Map<String, Object> map) {
        Integer week=new Integer(map.get("selectWeek").toString());
        List<Class> classList1 = classService.selectClassByUsernameAndSemesterAndWeek(
                map.get("userName").toString(),
                map.get("semester").toString(),week);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("course", classList1);
        return modelMap;
    }

    @RequestMapping(value = "/semester/getThisWeekClassByUsername", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getThisWeekClassByUsername(@RequestBody Map<String, Object> map) {
        List<Class> classList1 = classService.selectClassByUsernameAndWeek(map.get("userName").toString());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("course", classList1);
        return modelMap;
    }
    @RequestMapping(value = "/lab/getClassByLabId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getClassByUsernameToLab(@RequestBody Map<String, Object> map) {
        Integer labId = new Integer(map.get("labId").toString());
        List<Class> classList1 = classService.selectClassToLabAdmin(labId);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("course", classList1);
        return modelMap;
    }
    @RequestMapping(value = "/lab/getClassByLabIdAndSemester", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getClassByLabIdAndSemester(@RequestBody Map<String, Object> map) {
        Integer labId = new Integer(map.get("labId").toString());
        List<Class> classList1 = classService.selectClassToLabAdminBySemester(labId,map.get("semester").toString());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("course", classList1);
        return modelMap;
    }

    @RequestMapping(value = "/lab/getClassByLabIdAndSemesterAndWeek", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getClassByLabIdAndSemesterAndWeek(@RequestBody Map<String, Object> map) {
        Integer labId = new Integer(map.get("labId").toString());
        Integer week=new Integer(map.get("selectWeek").toString());
        List<Class> classList1 = classService.selectClassToLabAdminBySemesterAndWeek(labId,map.get("semester").toString(),week);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("course", classList1);
        return modelMap;
    }

    @RequestMapping(value = "/getCourseTableByUsername", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getCourseTableByUsername(@RequestBody Map<String, Object> map) {
        CourseTable courseTable = courseTableService.getCourseTable(
                map.get("userName").toString(), 1
        );

        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("courseTable", courseTable);
        return modelMap;
    }

    @RequestMapping(value = "/semester/getCourseTableByUsername", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getCourseTableByUsernameAndSemester(@RequestBody Map<String, Object> map) {
        CourseTable courseTable = courseTableService.getCourseTableBySemester(
                map.get("userName").toString(),
                1,
                map.get("semester").toString()
        );

        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("courseTable", courseTable);
        return modelMap;
    }

    @RequestMapping(value = "/getCourseTableByLabId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getCourseTableByLabId(@RequestBody Map<String, Object> map) {
        CourseTable courseTable = courseTableService.getCourseTable(
                new Integer(map.get("labId").toString()), 1
        );
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("courseTable", courseTable);
        return modelMap;
    }

    @RequestMapping(value = "/semester/getCourseTableByLab", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getCourseTableByLabIdAndSemester(@RequestBody Map<String, Object> map) {
        CourseTable courseTable = courseTableService.getCourseTableBySemester(
                new Integer(map.get("labId").toString()),
                1,
                map.get("semester").toString()
        );
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("courseTable", courseTable);
        return modelMap;
    }

    @RequestMapping(value = "/setWeek", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> setWeek() {
        systemParameterService.setSemester(0);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        return modelMap;
    }

    @RequestMapping(value = "/getClassByStudentId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getClassByStudentId(@RequestBody Map<String, Object> map) {
        SystemParameter systemParameter = systemParameterService.getSystemParameter();
        Semester semester = semesterService.getSemesterById(systemParameter.getThisSemesterId());
        List<Class> classList1 = classService.selectClassByStudentIdAndSemester(map.get("studentId").toString(),semester.getSemesterString());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("course", classList1);
        return modelMap;
    }
    @RequestMapping(value = "/getClassByStudentIdAndSemester", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getClassByStudentIdAndSemester(@RequestBody Map<String, Object> map) {
        List<Class> classList1 = classService.selectClassByStudentIdAndSemester(map.get("studentId").toString(),map.get("semester").toString());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("course", classList1);
        return modelMap;
    }

}
