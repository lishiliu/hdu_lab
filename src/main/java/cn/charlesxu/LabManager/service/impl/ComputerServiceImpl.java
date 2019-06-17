package cn.charlesxu.LabManager.service.impl;

import cn.charlesxu.LabManager.dao.ComputerDao;
import cn.charlesxu.LabManager.entity.Computer;
import cn.charlesxu.LabManager.entity.define.ComputerStatusDefine;
import cn.charlesxu.LabManager.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyan on 2019/6/2.
 */
@Service
public class ComputerServiceImpl implements ComputerService {
    @Autowired
    ComputerDao computerDao;

    @Override
    public String addComputer(Computer computer) {
        Computer request=new Computer();
        request.setComputerIp(computer.getComputerIp());
        List<Computer> computerList=computerDao.selectByRequest(request);
        String msg="";
        if(computerList!=null){
            msg="IP已存在！";
        }else{
            computerDao.insertSelective(computer);
            msg="插入成功！";
        }
        return msg;
    }

    @Override
    public String updateComputer(Computer computer) {
        int flag=0;
        String msg="";
        if(computer.getComputerIp()!=null){
            Computer request=new Computer();
            request.setComputerIp(computer.getComputerIp());
            List<Computer> computerList=computerDao.selectByRequest(request);
            if(computerList!=null&&computerList.size()>=2){
                msg="IP已存在！";
            }else{
                flag=1;
            }
        }else{
            flag=1;
        }
       if(flag==1){
           computerDao.updateById(computer);
           msg="修改成功！";
       }
        return msg;
    }

    @Override
    public int deleteComputer(Computer computer) {
        return computerDao.deleteById(computer.getId());
    }

    @Override
    public List<Computer> selectComputerByLabId(Integer labId) {
        Computer computer=new Computer();
        computer.setLabId(labId);
        List<Computer> computerList=computerDao.selectByRequest(computer);
        for(int i=0;i<computerList.size();i++){
            if(computerList.get(i).getStatus()==0){
                computerList.get(i).setStatusString(ComputerStatusDefine.Status_0);
            }
            if(computerList.get(i).getStatus()==1){
                computerList.get(i).setStatusString(ComputerStatusDefine.Status_1);
            }
        }
        return computerList;
    }

    @Override
    public Computer selectComputerByIp(String computerIp) {
        Computer computer=new Computer();
        computer.setComputerIp(computerIp);
        List<Computer> computerList=computerDao.selectByRequest(computer);
        if(computerList.size()>0){
            return computerList.get(0);
        }else{
            return  null;
        }

    }

    @Override
    public int selectCountByRequest(Computer computer) {
        return computerDao.selectCountByRequest(computer);
    }
}
