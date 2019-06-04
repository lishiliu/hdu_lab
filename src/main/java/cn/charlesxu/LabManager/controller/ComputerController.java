package cn.charlesxu.LabManager.controller;

import cn.charlesxu.LabManager.entity.Computer;
import cn.charlesxu.LabManager.entity.Lab;
import cn.charlesxu.LabManager.entity.form.Semester1;
import cn.charlesxu.LabManager.service.ComputerService;
import cn.charlesxu.LabManager.service.LabService;
import cn.charlesxu.LabManager.service.SemesterService;
import cn.charlesxu.LabManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyan on 2018/3/9.
 */
@Controller
@CrossOrigin(maxAge = 3600)
@RequestMapping("/computer")
public class ComputerController {
    @Autowired
    private ComputerService computerService;

    @Autowired
    private UserService userService;

    @Autowired
    private LabService labService;

    @RequestMapping(value = "/getComputer", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getComputer(HttpServletRequest request) {
        String computerIp=userService.getIp2(request);
        Computer computer=computerService.selectComputerByIp(computerIp);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if(computer!=null){
            Lab lab=labService.selectLabByLabId(computer.getLabId());
            modelMap.put("result", "success");
            modelMap.put("NowComputer", computer);
            modelMap.put("labName",lab.getLabName());
        }else{
            modelMap.put("msg", "请使用实验室设备签到！");
        }
        return modelMap;
    }

}
