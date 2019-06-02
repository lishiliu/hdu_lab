package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.entity.Computer;

import java.util.List;

/**
 * Created by liyan on 2019/1/30.
 */
public interface ComputerService {
    String addComputer(Computer computer);//添加设备

    String updateComputer(Computer computer);//更新设备信息

    int deleteComputer(Computer computer);//删除设备

    List<Computer> selectComputerByLabId(Integer labId);//根据实验室查询设备

    String  selectNumByIp(String computerIp);//根据设备Ip获取座位号

}
