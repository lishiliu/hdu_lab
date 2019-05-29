package cn.charlesxu.LabManager.entity;

import java.util.List;

/**
 * 预约细则
 * Created by liyan on 2018/1/27.
 */
public class OrderDetail {
    private int id;//编号
    private int orderId;//预约单id
    private String orderWeekString;//预约周次
    private String weekDaysString;//星期几
    private String classNumString;//上课节次
    private Integer firstLab;//Lab1
    private Integer firstLabPeoCount;//Lab1人数
    private Integer secondLab;//Lab2
    private Integer secondLabPeoCount;//Lab2人数
    private Integer thirdLab;//Lab3
    private Integer thirdLabPeoCount;//Lab3人数
    private int type;//志愿类型
    private List<Integer> orderWeek;//预约周次
    private List<Integer> weekDays;//星期几
    private List<Integer> classNum;//上课节次

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderWeekString() {
        return orderWeekString;
    }

    public void setOrderWeekString(String orderWeekString) {
        this.orderWeekString = orderWeekString;
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

    public Integer getFirstLab() {
        return firstLab;
    }

    public void setFirstLab(Integer firstLab) {
        this.firstLab = firstLab;
    }

    public Integer getFirstLabPeoCount() {
        return firstLabPeoCount;
    }

    public void setFirstLabPeoCount(Integer firstLabPeoCount) {
        this.firstLabPeoCount = firstLabPeoCount;
    }

    public Integer getSecondLab() {
        return secondLab;
    }

    public void setSecondLab(Integer secondLab) {
        this.secondLab = secondLab;
    }

    public Integer getSecondLabPeoCount() {
        return secondLabPeoCount;
    }

    public void setSecondLabPeoCount(Integer secondLabPeoCount) {
        this.secondLabPeoCount = secondLabPeoCount;
    }

    public Integer getThirdLab() {
        return thirdLab;
    }

    public void setThirdLab(Integer thirdLab) {
        this.thirdLab = thirdLab;
    }

    public Integer getThirdLabPeoCount() {
        return thirdLabPeoCount;
    }

    public void setThirdLabPeoCount(Integer thirdLabPeoCount) {
        this.thirdLabPeoCount = thirdLabPeoCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getOrderWeek() {
        return orderWeek;
    }

    public void setOrderWeek(List<Integer> orderWeek) {
        this.orderWeek = orderWeek;
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
        return "OrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderWeekString='" + orderWeekString + '\'' +
                ", weekDaysString='" + weekDaysString + '\'' +
                ", classNumString='" + classNumString + '\'' +
                ", firstLab=" + firstLab +
                ", firstLabPeoCount=" + firstLabPeoCount +
                ", secondLab=" + secondLab +
                ", secondLabPeoCount=" + secondLabPeoCount +
                ", thirdLab=" + thirdLab +
                ", thirdLabPeoCount=" + thirdLabPeoCount +
                ", type=" + type +
                ", orderWeek=" + orderWeek +
                ", weekDays=" + weekDays +
                ", classNum=" + classNum +
                '}';
    }
}
