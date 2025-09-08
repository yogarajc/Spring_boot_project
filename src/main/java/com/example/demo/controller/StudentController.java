package com.example.demo.controller;

import com.example.demo.BO.StudentBo;
import com.example.demo.DTO.ResponseDTO;
import com.example.demo.DTO.StudentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.JstlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentBo studentBo;

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
    List<StudentDTO> Studentslist = new ArrayList<>();

    @PostMapping("/students")
    public ResponseDTO addStudent(@RequestBody StudentDTO studentDTO){
        log.info("Insert Api student initiated");
        return studentBo.addStudent(studentDTO);
    }

    @GetMapping("/students")
    public List<StudentDTO> getAllStudents(){
        return studentBo.getAllStudents();
    }

    @GetMapping("/findStudentById/{id}")
    public Object findStudentById(@PathVariable(name = "id") String id){
        return studentBo.findStudentById(id);
    }
}
