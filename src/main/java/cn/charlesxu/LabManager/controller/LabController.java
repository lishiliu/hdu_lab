package cn.charlesxu.LabManager.controller;

import cn.charlesxu.LabManager.entity.Lab;
import cn.charlesxu.LabManager.entity.form.Lab1;
import cn.charlesxu.LabManager.entity.form.OrderForm1;
import cn.charlesxu.LabManager.entity.form.OrderForm2;
import cn.charlesxu.LabManager.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(maxAge = 3600)
@RequestMapping("/lab")
public class LabController {

    @Autowired
    LabService labService;

    //根据实验室种类得到所有实验室
    @RequestMapping(value = "/getLabByType", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getLabByType(@RequestBody OrderForm1 orderForm1) {
        List<String> labTypeList = orderForm1.getData().get("type");
        List<Lab1> labList1 = labService.selectLab1ByType(labTypeList);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时先认为都success
        modelMap.put("result", "success");
        modelMap.put("lab", labList1);
        return modelMap;
    }

    @RequestMapping(value = "/getLabOrderStatus", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getLabOrderStatus(@RequestBody OrderForm2 orderForm2) {
        List<String> labStatusStringList = labService.getLabOrderStatus(orderForm2);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //暂时先认为都success
        modelMap.put("result", "success");
        modelMap.put("status", labStatusStringList);
        return modelMap;
    }

    //得到所有实验室种类
    @RequestMapping(value = "/getAllLabType", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAllLabType() {
        List<String> labTypeList1 = labService.getAllLabType();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("labType", labTypeList1);
        return modelMap;
    }

    //得到所有实验室列表
    @RequestMapping(value = "/getAllLab", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAllLab() {
        List<Lab1> lab1List = labService.getAllLab1();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("lab1List", lab1List);
        return modelMap;
    }

    //根据实验室id得到实验室
    @RequestMapping(value = "/getLabById", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getLabById(@RequestBody Map<String, Object> map) {
        Lab1 lab1 = labService.getLabById(new Integer(map.get("labId").toString()));
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("lab", lab1);
        return modelMap;
    }

    //根据管理员工号得到实验室
    @RequestMapping(value = "/getLabByAdminUserName", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getLabByAdminUserName(@RequestBody Map<String, Object> map) {
        List<Lab1> lab1List = labService.getLab1ByAdminUserName(map.get("adminUserName").toString());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        modelMap.put("lab1List", lab1List);
        return modelMap;
    }


    //添加一个实验室
    @RequestMapping(value = "/addLab", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> addLab(@RequestBody Lab lab) {
        int result = labService.addLab(lab);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", result);
        return modelMap;
    }

    //修改一个实验室的信息
    @RequestMapping(value = "/updateLab", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateLab(@RequestBody Lab lab) {
        int result = labService.updateLab(lab);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", result);
        return modelMap;
    }

    //删除一个实验室
    @RequestMapping(value = "/deleteLab", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> deleteLab(@RequestBody Lab lab) {
        int result = labService.deleteLab(lab);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", result);
        return modelMap;
    }

}
