package cn.charlesxu.LabManager.entity.form;

import java.util.Date;
import java.util.List;

/**
 * Created by liyan on 2019/6/3.
 */
public class StudentSignInfoToStudent {
    private String classId;//课程号
    private String className;//课程名
    private String classWeekString;
    private String weekDaysString;
    private String classNumString;
    private List<Integer> classWeek;//课程周次
    private List<Integer> weekDays;//星期几
    private List<Integer> classNum;//课程节次
    private String userName;//教师号
    private Date createDate;
    private Date endDate;
    private int status;//状态
    private String statusString;//状态前端显示
    private String computerNo;//座位号
    private String labName;//实验室

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassWeekString() {
        return classWeekString;
    }

    public void setClassWeekString(String classWeekString) {
        this.classWeekString = classWeekString;
    }

    public String getWeekDaysString() {
        return weekDaysString;
    }

    public void setWeekDaysString(String weekDaysString) {
        this.weekDaysString = weekDaysString;
    }

    public String getClassNumString() {
        return classNumString;
    }

    public void setClassNumString(String classNumString) {
        this.classNumString = classNumString;
    }

    public List<Integer> getClassWeek() {
        return classWeek;
    }

    public void setClassWeek(List<Integer> classWeek) {
        this.classWeek = classWeek;
    }

    public List<Integer> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<Integer> weekDays) {
        this.weekDays = weekDays;
    }

    public List<Integer> getClassNum() {
        return classNum;
    }

    public void setClassNum(List<Integer> classNum) {
        this.classNum = classNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public String getComputerNo() {
        return computerNo;
    }

    public void setComputerNo(String computerNo) {
        this.computerNo = computerNo;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "StudentSignInfoToStudent{" +
                "classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", classWeekString='" + classWeekString + '\'' +
                ", weekDaysString='" + weekDaysString + '\'' +
                ", classNumString='" + classNumString + '\'' +
                ", classWeek=" + classWeek +
                ", weekDays=" + weekDays +
                ", classNum=" + classNum +
                ", userName='" + userName + '\'' +
                ", createDate=" + createDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", statusString='" + statusString + '\'' +
                ", computerNo='" + computerNo + '\'' +
                ", labName='" + labName + '\'' +
                '}';
    }
}
