package cn.charlesxu.LabManager.entity.form;

import java.util.List;

/**
 * Created by liyan on 2018/2/1.
 */
public class OrderDetail1 {
    // private int orderId;//预约单id
  /*  private int firstLab;//Lab1
    private int firstLabPeoCount;//Lab1人数
    private int secondLab;//Lab2
    private int secondLabPeoCount;//Lab2人数
    private int thirdLab;//Lab3
    private int thirdLabPeoCount;//Lab3人数*/
    private int type;//志愿类型
    private List<Integer> lab;
    private List<Integer> labArrangedPeoCount;
    private List<Integer> orderWeek;//预约周次
    private List<Integer> weekDays;//星期几
    private List<Integer> classNum;//课程节次

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getLab() {
        return lab;
    }

    public void setLab(List<Integer> lab) {
        this.lab = lab;
    }

    public List<Integer> getLabArrangedPeoCount() {
        return labArrangedPeoCount;
    }

    public void setLabArrangedPeoCount(List<Integer> labArrangedPeoCount) {
        this.labArrangedPeoCount = labArrangedPeoCount;
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
        return "OrderDetail1{" +
                "type=" + type +
                ", lab=" + lab +
                ", labArrangedPeoCount=" + labArrangedPeoCount +
                ", orderWeek=" + orderWeek +
                ", weekDays=" + weekDays +
                ", classNum=" + classNum +
                '}';
    }
}
