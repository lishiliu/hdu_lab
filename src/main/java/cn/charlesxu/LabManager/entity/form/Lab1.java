package cn.charlesxu.LabManager.entity.form;

/**
 * Created by
 * User: Charles Xu
 * Date: 31/1/2018
 * Time: 21:44
 */
public class Lab1 {
    private Integer id;

    private String userName;

    private String adminName;

    private String labName;

    private String labType;

    private Integer labPeoCount;

    private Integer labBuild;

    private Integer labNumber;

    private String labIntroduce;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getLabType() {
        return labType;
    }

    public void setLabType(String labType) {
        this.labType = labType;
    }

    public Integer getLabPeoCount() {
        return labPeoCount;
    }

    public void setLabPeoCount(Integer labPeoCount) {
        this.labPeoCount = labPeoCount;
    }

    public Integer getLabBuild() {
        return labBuild;
    }

    public void setLabBuild(Integer labBuild) {
        this.labBuild = labBuild;
    }

    public Integer getLabNumber() {
        return labNumber;
    }

    public void setLabNumber(Integer labNumber) {
        this.labNumber = labNumber;
    }

    public String getLabIntroduce() {
        return labIntroduce;
    }

    public void setLabIntroduce(String labIntroduce) {
        this.labIntroduce = labIntroduce;
    }
}
