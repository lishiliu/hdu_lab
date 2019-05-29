package cn.charlesxu.LabManager.entity;

import java.util.List;

public class Class {
    private Integer id;

    private String classId;//课程号

    private String className;//课程名

    private Integer classPeoCount;//课程人数

    private String userName;//教工号

    private String classWeekString;

    private String weekDaysString;

    private String classNumString;

    private List<Integer> classWeek;//课程周次

    private List<Integer> weekDays;//星期几

    private List<Integer> classNum;//课程节次

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getClassPeoCount() {
        return classPeoCount;
    }

    public void setClassPeoCount(Integer classPeoCount) {
        this.classPeoCount = classPeoCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", classPeoCount=" + classPeoCount +
                ", userName='" + userName + '\'' +
                ", classWeekString='" + classWeekString + '\'' +
                ", weekDaysString='" + weekDaysString + '\'' +
                ", classNumString='" + classNumString + '\'' +
                ", classWeek=" + classWeek +
                ", weekDays=" + weekDays +
                ", classNum=" + classNum +
                '}';
    }
}