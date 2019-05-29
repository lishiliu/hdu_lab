package cn.charlesxu.LabManager.entity;

import java.util.Date;
import java.util.List;

public class Semester {
    private Integer id;//编号

    private Integer beginYear;//开始年份

    private Integer endYear;//结束年份

    private Integer term;//学期

    private Date beginDate;//开始时间

    private Date endDate;//结束时间

    private String weekString;//周次(用于存入数据库)

    private String classNumString;//一天有多少节课(用于存入数据库)

    private List<Integer> week;//周次(用于存入前端数据)

    private List<Integer> classNum;//一天有多少节课(用于存入前端数据)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(Integer beginYear) {
        this.beginYear = beginYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getWeekString() {
        return weekString;
    }

    public void setWeekString(String weekString) {
        this.weekString = weekString == null ? null : weekString.trim();
    }

    public String getClassNumString() {
        return classNumString;
    }

    public void setClassNumString(String classNumString) {
        this.classNumString = classNumString == null ? null : classNumString.trim();
    }

    public List<Integer> getWeek() {
        return week;
    }

    public void setWeek(List<Integer> week) {
        this.week = week;
    }

    public List<Integer> getClassNum() {
        return classNum;
    }

    public void setClassNum(List<Integer> classNum) {
        this.classNum = classNum;
    }

    public String getSemesterString() {
        return beginYear + "-" + endYear + "-" + term;
    }

    @Override
    public String toString() {
        return "Semester{" +
                "id=" + id +
                ", beginYear=" + beginYear +
                ", endYear=" + endYear +
                ", term=" + term +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", weekString='" + weekString + '\'' +
                ", classNumString='" + classNumString + '\'' +
                ", week=" + week +
                ", classNum=" + classNum +
                '}';
    }
}