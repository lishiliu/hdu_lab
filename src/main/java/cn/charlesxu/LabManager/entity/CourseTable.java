package cn.charlesxu.LabManager.entity;

import java.util.ArrayList;

/**
 * Created by
 * User: Charles Xu
 * Date: 1/2/2018
 * Time: 21:00
 */
public class CourseTable {
    private ArrayList<ArrayList<ArrayList<Object>>> courseTable;

    public ArrayList<ArrayList<ArrayList<Object>>> getCourseTable() {
        return courseTable;
    }

    public void setCourseTable(ArrayList<ArrayList<ArrayList<Object>>> courseTable) {
        this.courseTable = courseTable;
    }
}
