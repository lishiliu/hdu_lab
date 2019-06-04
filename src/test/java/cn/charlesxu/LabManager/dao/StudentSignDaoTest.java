package cn.charlesxu.LabManager.dao;

import cn.charlesxu.LabManager.base.BaseTest;
import cn.charlesxu.LabManager.entity.Class;
import cn.charlesxu.LabManager.entity.StudentSign;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToStudent;
import cn.charlesxu.LabManager.entity.form.StudentSignInfoToTeacher;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyan on 2018/1/30.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentSignDaoTest extends BaseTest {

    @Autowired
    private StudentSignDao studentSignDao;


    @Test
    public void selectByRequest() {
        StudentSign studentSign=new StudentSign();
        studentSign.setStatus(1);
        studentSignDao.selectByRequest(studentSign);
        System.out.println(" ");
    }

    @Test
    public void selectStudentSignInfoToTeacher(){
        List<StudentSignInfoToTeacher> studentSignInfoToTeachers=studentSignDao.selectStudentSignInfoToTeacher("40387","(2017-2018-1)-S0500500-40387-1",3);
        System.out.println(studentSignInfoToTeachers);
    }
    @Test
    public void selectCountByRequest() {
        StudentSign studentSign=new StudentSign();
        studentSign.setStatus(1);
        int a=studentSignDao.selectCountByRequest(studentSign);
        System.out.println(a);
    }

    @Test
    public void selectStudentSignInfoToStudent(){
        List<StudentSignInfoToStudent> studentSignInfoToStudentList=studentSignDao.selectStudentSignInfoToStudent("15058215","2017-2018-1",3,1);
        System.out.println(studentSignInfoToStudentList);
    }

}
