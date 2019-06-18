package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.Class;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToTeacher;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyan on 2018/2/26.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentSignServiceTest extends BaseTest {
    @Autowired
    private StudentSignService studentSignService;

    @Test
    public void getSignRecordByTeacherAndClassAndWeek() {
        List<StudentSignInfoToTeacher> studentSignInfoToTeachers=studentSignService.getSignRecordByTeacherAndClassAndWeek("40387","(2017-2018-1)-S0500500-40387-1",3);
        System.out.println(studentSignInfoToTeachers);
    }
    @Test
    public void addStudent() {
        int res=studentSignService.addStudent("40387","(2017-2018-1)-S0500500-40387-1","15012345","王大雷");
        System.out.println(res);
    }
    @Test
    public void updateStatus() {
        int res=studentSignService.updateStatus("40387","(2017-2018-1)-S0500500-40387-1",1);
        System.out.println(res);
    }


}
