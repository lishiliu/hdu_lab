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
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/getComputerByLabId", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getComputerByLabId(@RequestBody Map<String, Object> map) {
        Integer labId = new Integer(map.get("labId").toString());
        List<Computer> computerList = computerService.selectComputerByLabId(labId);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时认为都是success的
        modelMap.put("result", "success");
        modelMap.put("computerList", computerList);
        return modelMap;
    }

    //添加一节课
    @RequestMapping(value = "/addComputer", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> addComputer(@RequestBody Computer computer) {
        String msg = computerService.addComputer(computer);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if(msg.equals("IP已存在！")){
            modelMap.put("result", "repeat");
        }else{
            modelMap.put("result", "success");
        }
        modelMap.put("msg", msg);
        return modelMap;
    }

    //删除设备
    @RequestMapping(value = "/deleteComputer", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> deleteClass(@RequestBody Computer computer) {
        int result = computerService.deleteComputer(computer);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", result);
        return modelMap;
    }

    //修改设备
    @RequestMapping(value = "/updateComputer", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateClass(@RequestBody Computer computer) {
        String msg = computerService.updateComputer(computer);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if(msg.equals("IP已存在！")){
            modelMap.put("result", "repeat");
        }else{
            modelMap.put("result", "success");
        }
        modelMap.put("msg", msg);
        return modelMap;
    }
    @RequestMapping(value = "/getComputerCount", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getComputerCount(@RequestBody Map<String, Object> map) {
        Integer labId = new Integer(map.get("labId").toString());
        Computer request=new Computer();
        request.setLabId(labId);
        request.setStatus(0);
        int undoCount=computerService.selectCountByRequest(request);
        request.setStatus(1);
        int doCount=computerService.selectCountByRequest(request);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("undoCount", undoCount);
        modelMap.put("doCount", doCount);
        return modelMap;
    }
}
