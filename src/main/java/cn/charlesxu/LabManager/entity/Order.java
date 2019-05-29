package cn.charlesxu.LabManager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 预约单
 * Created by liyan on 2018/1/27.
 */
public class Order {
    private int id;//编号
    private String userName;//教师号
    private String classId;//课程号
    private String className;//课程名称
    private String mainClassId;//主课程号
    private int mainClassOrder;//主课程号中的序号
    private int beginYear;//起始学年
    private int endYear;//结束学年
    private int term;//学期
    private String requireTagString;//设施标签String
    private List<Integer> requireTag;
    private int isMonopolize;//是否独占
    private int classPeoCount;//课程人数
    private int isAutoArrangeLab;//是否自动安排实验室
    private int isLabArranged;//实验室是否已被安排
    private int orderValue;//预约单权重和
    private int passFlag;//预约安排情况标识符
    private Date createTime;//创建时间
    private Date lastModify;//最后修改时间
    private String remark;//备注
    private List<OrderDetail> orderDetails;//预约细则列表，一般有三种

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

    public int getPassFlag() {
        return passFlag;
    }

    public void setPassFlag(int passFlag) {
        this.passFlag = passFlag;
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

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getMainClassId() {
        return mainClassId;
    }

    public void setMainClassId(String mainClassId) {
        this.mainClassId = mainClassId;
    }

    public int getMainClassOrder() {
        return mainClassOrder;
    }

    public void setMainClassOrder(int mainClassOrder) {
        this.mainClassOrder = mainClassOrder;
    }

    public int getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(int beginYear) {
        this.beginYear = beginYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getRequireTagString() {
        return requireTagString;
    }

    public void setRequireTagString(String requireTagString) {
        this.requireTagString = requireTagString;
    }

    public int getIsMonopolize() {
        return isMonopolize;
    }

    public void setIsMonopolize(int isMonopolize) {
        this.isMonopolize = isMonopolize;
    }

    public int getIsAutoArrangeLab() {
        return isAutoArrangeLab;
    }

    public void setIsAutoArrangeLab(int isAutoArrangeLab) {
        this.isAutoArrangeLab = isAutoArrangeLab;
    }

    public int getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(int orderValue) {
        this.orderValue = orderValue;
    }

    public int getIsLabArranged() {
        return isLabArranged;
    }

    public void setIsLabArranged(int isLabArranged) {
        this.isLabArranged = isLabArranged;
    }

    public List<Integer> getRequireTag() {
        return requireTag;
    }

    public void setRequireTag(List<Integer> requireTag) {
        this.requireTag = requireTag;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", mainClassId='" + mainClassId + '\'' +
                ", mainClassOrder=" + mainClassOrder +
                ", beginYear=" + beginYear +
                ", endYear=" + endYear +
                ", term=" + term +
                ", requireTagString='" + requireTagString + '\'' +
                ", requireTag=" + requireTag +
                ", isMonopolize=" + isMonopolize +
                ", classPeoCount=" + classPeoCount +
                ", isAutoArrangeLab=" + isAutoArrangeLab +
                ", isLabArranged=" + isLabArranged +
                ", orderValue=" + orderValue +
                ", passFlag=" + passFlag +
                ", createTime=" + createTime +
                ", lastModify=" + lastModify +
                ", remark='" + remark + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}


