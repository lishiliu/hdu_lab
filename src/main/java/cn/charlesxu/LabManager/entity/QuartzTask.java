package cn.charlesxu.LabManager.entity;

import java.util.Date;

/**
 * Created by liyan on 2019/5/31.
 */
public class QuartzTask {

    private Integer id;

    private String className;

    private String jobName;

    private String jobGroupName;

    private String cron;

    private Integer labId;

    private Integer status;

    private Date startDate;

    private Date endDate;

    private Date createTime;

    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        return "QuartzTask{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobGroupName='" + jobGroupName + '\'' +
                ", cron='" + cron + '\'' +
                ", labId=" + labId +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
