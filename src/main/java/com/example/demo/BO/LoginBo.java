package com.example.demo.BO;


import com.example.demo.DTO.LoginDTO;
import com.example.demo.DTO.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginBo {

    @Autowired
    LoginDTO loginDTO;

    @Autowired
    ResponseDTO responseDTO;

    public ResponseDTO LoginValidator(LoginDTO loginDTO){
        System.out.println(loginDTO.getUsername()+"Username");
        boolean validator = loginDTO.getUsername().equals("Admin")  & loginDTO.getPassword().equals("1234");
        if (validator){
            responseDTO.setStatus("Success");
            responseDTO.setMessage("Welcome admin!");
        }else {
            responseDTO.setStatus("Failure");
            responseDTO.setMessage("Invalid Credentials!");
        }
        return responseDTO;
    }
}
