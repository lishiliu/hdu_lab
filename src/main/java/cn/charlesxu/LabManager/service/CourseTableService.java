package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.entity.CourseTable;

/**
 * Created by
 * User: Charles Xu
 * Date: 1/2/2018
 * Time: 21:18
 */
public interface CourseTableService {
    CourseTable getCourseTable(String Username, int week);

    CourseTable getCourseTableBySemester(String Username, int week, String semesterString);

    CourseTable getCourseTable(int labId, int week);

    CourseTable getCourseTableBySemester(int labId, int week, String semesterString);
}
