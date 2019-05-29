package cn.charlesxu.LabManager.entity.form;

/**
 * Created by
 * User: Charles Xu
 * Date: 1/2/2018
 * Time: 21:00
 */
public class CourseTableCell {
    private String course;
    private String courseId;
    private String teacherName;
    private String weeks;
    private String place;
    private Integer row;
    public String tbstyle = "tb";

    //equals方法
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != CourseTableCell.class) {
            return false;
        } else {
            CourseTableCell courseTableCellCompare = (CourseTableCell) obj;
            if (courseTableCellCompare.getCourse() != this.getCourse()) {
                return false;
            } else if (courseTableCellCompare.getCourseId() != this.getCourseId()) {
                return false;
            } else if (courseTableCellCompare.getTeacherName() != this.getTeacherName()) {
                return false;
            } else if (courseTableCellCompare.getWeeks() != this.getWeeks()) {
                return false;
            } else if (courseTableCellCompare.getPlace() != this.getPlace()) {
                return false;
            } else {
                return true;
            }
        }

    }


    //复制构造函数
    public CourseTableCell(CourseTableCell courseTableCell) {
        this.course = courseTableCell.course;
        this.courseId = courseTableCell.courseId;
        this.teacherName = courseTableCell.teacherName;
        this.weeks = courseTableCell.weeks;
        this.place = courseTableCell.place;
        this.row = courseTableCell.row;
        this.tbstyle = courseTableCell.tbstyle;
    }

    //无参构造函数
    public CourseTableCell() {
        this.course = null;
        this.courseId = null;
        this.teacherName = null;
        this.weeks = null;
        this.place = null;
        this.row = 0;
        this.tbstyle = "tb";
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public String getTbstyle() {
        return tbstyle;
    }

    public void setTbstyle(String tbstyle) {
        this.tbstyle = tbstyle;
    }
}
