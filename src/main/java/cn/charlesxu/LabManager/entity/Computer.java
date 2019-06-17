package cn.charlesxu.LabManager.entity;

import java.util.Date;

public class Computer {
    private Integer id;

    private Integer labId;

    private String computerIp;

    private String computerNum;

    private String software;

    private String type;

    private String systemType;

    private Integer status;

    private  String statusString;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    public String getComputerIp() {
        return computerIp;
    }

    public void setComputerIp(String computerIp) {
        this.computerIp = computerIp;
    }

    public String getComputerNum() {
        return computerNum;
    }

    public void setComputerNum(String computerNum) {
        this.computerNum = computerNum;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", labId=" + labId +
                ", computerIp='" + computerIp + '\'' +
                ", computerNum='" + computerNum + '\'' +
                ", software='" + software + '\'' +
                ", type='" + type + '\'' +
                ", systemType='" + systemType + '\'' +
                ", status=" + status +
                ", statusString='" + statusString + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}