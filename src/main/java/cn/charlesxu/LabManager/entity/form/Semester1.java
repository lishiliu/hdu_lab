package cn.charlesxu.LabManager.entity.form;

/**
 * Created by liyan on 2018/3/9.
 */
public class Semester1 {
    private String nowSemester;
    private Integer maxWeek;

    public String getNowSemester() {
        return nowSemester;
    }

    public void setNowSemester(String nowSemester) {
        this.nowSemester = nowSemester;
    }

    public Integer getMaxWeek() {
        return maxWeek;
    }

    public void setMaxWeek(Integer maxWeek) {
        this.maxWeek = maxWeek;
    }

    @Override
    public String toString() {
        return "Semester1{" +
                "nowSemester='" + nowSemester + '\'' +
                ", maxWeek=" + maxWeek +
                '}';
    }
}
