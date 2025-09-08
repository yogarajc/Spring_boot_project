package com.example.demo.BO;

import com.example.demo.DTO.ResponseDTO;
import com.example.demo.DTO.StudentDTO;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StudentBo {
    private static final Logger log = LoggerFactory.getLogger(StudentBo.class);
    Map<String,StudentDTO> StudentsMap = new HashMap<>();
    List<StudentDTO> Studentslist = new ArrayList<>();
    public ResponseDTO addStudent(StudentDTO studentDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (StudentsMap.containsKey(studentDTO.getId())){
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Student ID is already present, enter new ID");
            }
             else if (studentDTO.getName() == null || studentDTO.getName().isEmpty() && studentDTO.getId().isEmpty()){
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Student name and ID is Empty, Cannot add student details");
            }
            else if (studentDTO.getName().isEmpty()){
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Student ID "+studentDTO.getId()+" Name field is Empty, Cannot add student details");
            }
            else if (studentDTO.getId().isEmpty()){
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Student Name "+studentDTO.getName()+" ID field is Empty, Cannot add student details");
            }
            else {
               StudentsMap.put(studentDTO.getId(),studentDTO);
                Studentslist.add(studentDTO);
                responseDTO.setStatus("Success");
                responseDTO.setMessage("Student Added Successfully");
            }
        } catch (Exception exc){
            log.info("Exception "+exc);
            responseDTO.setStatus("Exception");
            responseDTO.setMessage(String.valueOf(exc));
        }
        return responseDTO;
    }

    public List<StudentDTO> getAllStudents(){
        return Studentslist;
    }

    public Object findStudentById(String id){
        ResponseDTO responseDTO = new ResponseDTO();
        if (StudentsMap.containsKey(id)){
            return StudentsMap.get(id);
        }
        else {
            responseDTO.setStatus("Failure");
            responseDTO.setMessage("Student not found");
            return responseDTO;
        }
    }
}
