package cn.charlesxu.LabManager.entity.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by liyan on 2018/2/1.
 */
public class Order1 {
    private int id;//编号
    private String userName;//教师号
    private String classId;//课程号
    private String className;//课程名称
    private int classPeoCount;//课程人数
    private String remark;//备注
    private String passFlagString;//预约安排情况标识符
    private Date createTime;//创建时间
    private Date lastModify;//最后修改时间
    private List<OrderDetail1> orderDetails;//预约细则列表，一般有三种

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public int getClassPeoCount() {
        return classPeoCount;
    }

    public void setClassPeoCount(int classPeoCount) {
        this.classPeoCount = classPeoCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPassFlagString() {
        return passFlagString;
    }

    public void setPassFlagString(String passFlagString) {
        this.passFlagString = passFlagString;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

    public List<OrderDetail1> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail1> orderDetails) {
        this.orderDetails = orderDetails;
    }


}
