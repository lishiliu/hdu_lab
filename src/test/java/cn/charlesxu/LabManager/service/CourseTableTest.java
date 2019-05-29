package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.CourseTable;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * User: Charles Xu
 * Date: 2/2/2018
 * Time: 19:33
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseTableTest extends BaseTest {
    @Autowired
    private CourseTableService courseTableService;

    @Test
    public void testCourseTable() {
        //CourseTable courseTableObject = courseTableService.getCourseTable("40392",1);
        CourseTable courseTableObject = courseTableService.getCourseTable(5, 1);
        ArrayList<ArrayList<ArrayList<Object>>> courseTable = courseTableObject.getCourseTable();
        for (int i = 0; i < courseTable.size(); i++) {
            System.out.print("第" + i + "节:");
            ArrayList<ArrayList<Object>> column = courseTable.get(i);
            for (int j = 0; j < column.size(); j++) {
                System.out.print("{");
                List<Object> cell = column.get(j);
                for (int k = 0; k < cell.size(); k++) {
                    System.out.print(k + ": ");
                    System.out.print(cell.get(k));
                }
                System.out.print("},");
            }
            System.out.println();
        }
    }
}
