package com.example.springBootDb.springBootDataBase.service;


import com.example.springBootDb.springBootDataBase.entity.Student;
import com.example.springBootDb.springBootDataBase.repos.StudentRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepos studentRepos;
//    public StudentService(StudentRepos studentRepos){
//        this.studentRepos = studentRepos;
//    }

    public Student createStudent(Student stu) {
        return studentRepos.save(stu);
    }


    public Student getStudentById(Long id) {
        return studentRepos.findById(id)
                .orElse(null);
    }

    public List<Student> getAll() {
        return studentRepos.findAll();
    }

    // Return boolean so controller decides 200 or 404
    public boolean deleteById(Long id) {
        if (!studentRepos.existsById(id)) {
            return false;
        }
        studentRepos.deleteById(id);
        return true;
    }


    public Student updateById(Student stu, Long id) {
        Student student = studentRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));

        student.setName(stu.getName());
        student.setMarks(stu.getMarks());

        return studentRepos.save(student);


    }

    public List<Student> findStudentByCourse(String course) {
        return studentRepos.findByCourseContainingIgnoreCase(course);
    }
}
