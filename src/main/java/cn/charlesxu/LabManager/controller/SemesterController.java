package cn.charlesxu.LabManager.controller;

import cn.charlesxu.LabManager.entity.form.Semester1;
import cn.charlesxu.LabManager.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyan on 2018/3/9.
 */
@Controller
@CrossOrigin(maxAge = 3600)
@RequestMapping("/semester")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;

    @RequestMapping(value = "/getNowSemester", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNowSemester() {
        Semester1 semester1 = semesterService.getNowSemester();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("NowSemester", semester1);
        return modelMap;
    }

}
