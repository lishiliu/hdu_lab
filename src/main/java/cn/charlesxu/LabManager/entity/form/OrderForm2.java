package cn.charlesxu.LabManager.entity.form;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Charles Xu
 * Date: 2018/3/9
 * Time: 11:26
 */
public class OrderForm2 {
    private Integer labId;
    private List<Integer> week;
    private List<Integer> weekday;
    private List<Integer> classNum;

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    public List<Integer> getWeek() {
        return week;
    }

    public void setWeek(List<Integer> week) {
        this.week = week;
    }

    public List<Integer> getWeekday() {
        return weekday;
    }

    public void setWeekday(List<Integer> weekday) {
        this.weekday = weekday;
    }

    public List<Integer> getClassNum() {
        return classNum;
    }

    public void setClassNum(List<Integer> classNum) {
        this.classNum = classNum;
    }
}
