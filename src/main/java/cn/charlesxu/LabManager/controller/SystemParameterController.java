package cn.charlesxu.LabManager.controller;

import cn.charlesxu.LabManager.entity.SystemParameter;
import cn.charlesxu.LabManager.service.SystemParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by
 * User: Charles Xu
 * Date: 3/3/2018
 * Time: 08:26
 */
@Controller
@CrossOrigin(maxAge = 3600)
@RequestMapping("/system/parameter")
public class SystemParameterController {
    @Autowired
    private SystemParameterService systemParameterService;

    @RequestMapping(value = "/getParameter", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getParameter() {
        SystemParameter systemParameter = systemParameterService.getSystemParameter();

        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("Parameter", systemParameter);
        return modelMap;
    }

}
