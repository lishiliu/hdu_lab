package cn.charlesxu.LabManager.service.impl;

import cn.charlesxu.LabManager.dao.StudentDao;
import cn.charlesxu.LabManager.entity.Student;
import cn.charlesxu.LabManager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by liyan on 2019/6/2.
 */
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;


    @Override
    public Student getStudentByStudentId(String studentId) {
        Student student=new Student();
        student.setStudentId(studentId);
        List<Student> result=studentDao.selectByRequest(student);
        return result.get(0);
    }

    @Override
    public int login(String studentId, String password) {
        int result=0;
        Student student=new Student();
        student.setStudentId(studentId);
        student.setPassword(password);
        List<Student> resultList=studentDao.selectByRequest(student);
        if(resultList==null){
            result=-1;
        }
        return result;
    }

    @Override
    public int addUser(Student student) {
        Student request=new Student();
        request.setStudentId(student.getStudentId());
        int result = 0;
        if (studentDao.selectByRequest(request) != null) {
            result = -1;
        }
        if (result == 0) {
            result = studentDao.insertSelective(student);
        }
        return result;
    }

    @Override
    public int updatePassword(String studentId, String oldPassword, String newPassword) {
        int result=0;
        Student student=new Student();
        student.setStudentId(studentId);
        student.setPassword(oldPassword);
        List<Student> resultList=studentDao.selectByRequest(student);
        if (resultList != null) {
            student.setId(resultList.get(0).getId());
            student.setPassword(newPassword);
            result=studentDao.updateById(student);
        } else {
          result=-1;
        }
        return result;
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.updateById(student);
    }
}
