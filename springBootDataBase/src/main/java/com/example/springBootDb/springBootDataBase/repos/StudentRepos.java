package com.example.springBootDb.springBootDataBase.repos;

import com.example.springBootDb.springBootDataBase.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepos extends JpaRepository<Student,Long> {

    List<Student> findByCourseContainingIgnoreCase(String  course);
}
