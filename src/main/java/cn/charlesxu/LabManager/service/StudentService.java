package cn.charlesxu.LabManager.service;

import cn.charlesxu.LabManager.entity.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liyan on 2019/6/2.
 */
public interface StudentService {
    Student getStudentByStudentId(String studentId);

    int login(String studentId, String password);

    int addUser(Student student);

    int updatePassword(String studentId, String oldPassword, String newPassword);

    int updateStudent(Student student);

}
