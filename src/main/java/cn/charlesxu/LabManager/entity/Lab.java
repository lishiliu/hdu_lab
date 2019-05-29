package cn.charlesxu.LabManager.entity;

public class Lab {
    private Integer id;

    private String adminUserName;

    private String labName;

    private String labType;

    private Integer labPeoCount;

    private Integer labBuild;

    private Integer labNumber;

    private String labIntroduce;

    private int labUsage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName == null ? null : adminUserName.trim();
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName == null ? null : labName.trim();
    }

    public String getLabType() {
        return labType;
    }

    public void setLabType(String labType) {
        this.labType = labType == null ? null : labType.trim();
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
        this.labIntroduce = labIntroduce == null ? null : labIntroduce.trim();
    }

    public int getLabUsage() {
        return labUsage;
    }

    public void setLabUsage(int labUsage) {
        this.labUsage = labUsage;
    }
}