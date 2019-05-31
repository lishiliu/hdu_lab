package cn.charlesxu.LabManager.entity;

import java.util.Date;

public class Computer {
    private Integer id;

    private Integer labId;

    private String computerIp;

    private String computerNum;

    private String software;

    private String type;

    private String system;

    private Byte status;

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
        this.computerIp = computerIp == null ? null : computerIp.trim();
    }

    public String getComputerNum() {
        return computerNum;
    }

    public void setComputerNum(String computerNum) {
        this.computerNum = computerNum == null ? null : computerNum.trim();
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software == null ? null : software.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system == null ? null : system.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
                ", system='" + system + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}