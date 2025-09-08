package com.example.demo.controller;

import com.example.demo.BO.CalcBo;
import com.example.demo.DTO.CalcDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {

    @Autowired
    CalcDTO calcDTO;

    @Autowired
    CalcBo calcBo;

    @GetMapping(path = "/api/calc")
    public CalcDTO getCalcValue(@RequestParam (name = "a") String a ,@RequestParam(name = "b") String b ){
        calcDTO.setA(Integer.valueOf(a));
        calcDTO.setB(Integer.valueOf(b));
        return calcBo.Calculator(calcDTO);
    }
}
