package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.entity.SystemParameter;

/**
 * Created by
 * User: Charles Xu
 * Date: 3/2/2018
 * Time: 19:27
 */
public interface SystemParameterService {
    int setSemester(int semesterId);

    SystemParameter getSystemParameter();
}
