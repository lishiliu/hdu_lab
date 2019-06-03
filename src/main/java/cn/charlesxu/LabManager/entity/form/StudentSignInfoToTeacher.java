package cn.charlesxu.LabManager.entity.form;

/**
 * Created by liyan on 2019/6/3.
 */
public class StudentSignInfoToTeacher {
    private String studentId;//学号
    private String studentName;//姓名
    private String classNo;//班级
    private String majorIn;//专业
    private String college;//学院
    private String gradeNo;//年级
    private int status;//状态
    private String statusString;//状态前端显示
    private String computerNo;//座位号
    private String labName;//实验室

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getMajorIn() {
        return majorIn;
    }

    public void setMajorIn(String majorIn) {
        this.majorIn = majorIn;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getGradeNo() {
        return gradeNo;
    }

    public void setGradeNo(String gradeNo) {
        this.gradeNo = gradeNo;
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

    @Override
    public String toString() {
        return "StudentSignInfoToTeacher{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", classNo='" + classNo + '\'' +
                ", majorIn='" + majorIn + '\'' +
                ", college='" + college + '\'' +
                ", gradeNo='" + gradeNo + '\'' +
                ", status=" + status +
                ", statusString='" + statusString + '\'' +
                ", computerNo='" + computerNo + '\'' +
                ", labName='" + labName + '\'' +
                '}';
    }
}
