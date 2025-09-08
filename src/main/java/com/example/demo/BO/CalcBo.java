package com.example.demo.BO;

import com.example.demo.DTO.CalcDTO;
import org.springframework.stereotype.Component;

@Component
public class CalcBo {
    public CalcDTO Calculator(CalcDTO calcDTO){
        int a = calcDTO.getA();
        int b = calcDTO.getB();
        calcDTO.setSum(a+b); //Sum
        calcDTO.setDifference(a-b); //difference
        calcDTO.setProduct((double) (a * b)); //product
        calcDTO.setQuotient((double) a/b); //quotient
        return calcDTO;
    }
}
