package cn.charlesxu.LabManager.entity;

/**
 * Created by
 * User: Charles Xu
 * Date: 3/2/2018
 * Time: 14:18
 */

/**
 * 系统参数类
 */

public class SystemParameter {
    private int id;
    private int thisSemesterId;//当前正在进行的学期Id
    private int nowOrderSemesterId;//当前正在预约的学期Id
    private int thisWeek;//计算得出

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThisSemesterId() {
        return thisSemesterId;
    }

    public void setThisSemesterId(int thisSemesterId) {
        this.thisSemesterId = thisSemesterId;
    }

    public int getThisWeek() {
        return thisWeek;
    }

    public void setThisWeek(int thisWeek) {
        this.thisWeek = thisWeek;
    }

    public int getNowOrderSemesterId() {
        return nowOrderSemesterId;
    }

    public void setNowOrderSemesterId(int nowOrderSemesterId) {
        this.nowOrderSemesterId = nowOrderSemesterId;
    }

}
