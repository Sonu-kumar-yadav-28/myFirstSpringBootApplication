package com.example.springBootDb.springBootDataBase.controller;

import com.example.springBootDb.springBootDataBase.entity.Student;
import com.example.springBootDb.springBootDataBase.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class studentController {

    @Autowired
    private StudentService studentService;

    //201 Created
    @PostMapping
    public ResponseEntity<Student> create( @Valid @RequestBody  Student stu){


          Student  saved = studentService.createStudent(stu);
          return new ResponseEntity<>(saved,HttpStatus.CREATED);

    }

    //200 OK or 404 -Not found-Use when the requested resource does NOT exist
    @GetMapping("id/{myid}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long myid){
        Student saved = studentService.getStudentById(myid);
        if(saved==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(saved,HttpStatus.OK);
    }

    // 200 OK or 204 NO CONTENT
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> saved =  studentService.getAll();
        if(saved.isEmpty()){
            return new ResponseEntity<>(saved,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(saved,HttpStatus.OK);
    }


    @DeleteMapping("id/{myid}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long myid){
          boolean deleted= studentService.deleteById(myid);
         if(!deleted){
             return new ResponseEntity<>("Student Not Found ",HttpStatus.NOT_FOUND);
         }
         return new ResponseEntity<>("Deleted Successfully..",HttpStatus.OK);
    }


    @PutMapping("id/{id}")
    public ResponseEntity<Student> updateById(@RequestBody Student stu, @PathVariable Long id) {
        Student updated = studentService.updateById(stu, id);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("search/course")
    public ResponseEntity<List<Student>> getByCourses(@RequestParam String course) {
        List<Student> students = studentService.findStudentByCourse(course);
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }




}
