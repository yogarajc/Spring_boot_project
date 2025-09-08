package com.example.demo.controller;

import com.example.demo.BO.LoginBo;
import com.example.demo.DTO.GreetingsDTO;
import com.example.demo.DTO.LoginDTO;
import com.example.demo.DTO.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    LoginBo loginBo;


    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/username/{id}")
    public String UserId(@PathVariable String id){
        return "Hello "+id;
    }

    @GetMapping("/api/requestParam")
    public String reqParam(@RequestParam(name = "id") String id,String name){
        return "User Id extracted using request param annotation "+ id;
    }

    //Gets the value from URL (api/greetings/111 )
    @GetMapping("api/greetings/{orginalMessage}")
    public GreetingsDTO getMessage(@PathVariable(name = "orginalMessage") String orginalMessage){
        GreetingsDTO greetingsDTO = new GreetingsDTO();
        greetingsDTO.setOrginalMessage(orginalMessage);
        String returnMessage = "You Said : "+orginalMessage;
        greetingsDTO.setResponseMessage(returnMessage);
        return greetingsDTO;
    }
    //Gets the value from the URL and can use it in the method(api/reqParam?ResponseMessage=abc)
    @GetMapping("/api/reqParam/")
    public GreetingsDTO reqparam(@RequestParam(name = "ResponseMessage") String ResponseMessage){
        GreetingsDTO greetingsDTO = new GreetingsDTO();
        greetingsDTO.setResponseMessage(ResponseMessage);
        return greetingsDTO;
    }

    @PostMapping(path = "/api/login", produces = "application/json")
    public ResponseDTO login(@RequestBody LoginDTO loginDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        System.out.println(loginDTO.getPassword());
        return loginBo.LoginValidator(loginDTO);
    }

}
