package cn.charlesxu.LabManager.entity;

import java.util.Date;

public class StudentSign {
    private Integer id;

    private String studentId;

    private String teacherId;

    private String classId;

    private Integer labId;

    private Integer beginYear;

    private Integer endYear;

    private Byte term;

    private Integer week;

    private Byte weekDays;

    private String classNum;

    private Date workDate;

    private String computerNo;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
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

    public Byte getTerm() {
        return term;
    }

    public void setTerm(Byte term) {
        this.term = term;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Byte getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(Byte weekDays) {
        this.weekDays = weekDays;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum == null ? null : classNum.trim();
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getComputerNo() {
        return computerNo;
    }

    public void setComputerNo(String computerNo) {
        this.computerNo = computerNo == null ? null : computerNo.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "StudentSign{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", classId='" + classId + '\'' +
                ", labId=" + labId +
                ", beginYear=" + beginYear +
                ", endYear=" + endYear +
                ", term=" + term +
                ", week=" + week +
                ", weekDays=" + weekDays +
                ", classNum='" + classNum + '\'' +
                ", workDate=" + workDate +
                ", computerNo='" + computerNo + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}