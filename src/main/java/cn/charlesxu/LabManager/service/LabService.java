package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.entity.Lab;
import cn.charlesxu.LabManager.entity.Order;
import cn.charlesxu.LabManager.entity.OrderDetail;
import cn.charlesxu.LabManager.entity.form.Lab1;
import cn.charlesxu.LabManager.entity.form.OrderForm2;

import java.util.List;

public interface LabService {
    int addLab(Lab lab);//添加实验室

    int updateLab(Lab lab);//实验室信息修改

    int deleteLab(Lab lab);//删除一个实验室

    Lab1 getLabById(Integer labId);

    List<Lab> selectLabByType(String type);//根据实验室种类查询实验室列表

    List<Lab> selectLabByTag(List<Integer> tagList);

    Lab selectLabByLabId(int labId);

    List<Lab1> selectLab1ByType(List<String> labTypeList);

    List<String> getLabOrderStatus(OrderForm2 orderForm2);

    List<String> getAllLabType();//获得所有实验室种类

    List<Lab1> getAllLab1();//获取所有实验列表

    List<Lab1> getLab1ByAdminUserName(String adminUserName);

    int calculateLabArrangedUsage(int labId, String semester);

    int calculateLabUsageMethod(OrderDetail orderDetail);

}
