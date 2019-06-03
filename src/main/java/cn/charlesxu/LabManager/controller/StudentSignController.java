package cn.charlesxu.LabManager.controller;

import cn.charlesxu.LabManager.entity.Class;
import cn.charlesxu.LabManager.entity.form.Semester1;
import cn.charlesxu.LabManager.service.SemesterService;
import cn.charlesxu.LabManager.service.StudentSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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


}
