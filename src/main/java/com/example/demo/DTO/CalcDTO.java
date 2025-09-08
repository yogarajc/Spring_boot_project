package com.example.demo.DTO;

import org.springframework.stereotype.Component;

@Component
public class CalcDTO {
    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getDifference() {
        return difference;
    }

    public void setDifference(Integer difference) {
        this.difference = difference;
    }

    public Double getProduct() {
        return product;
    }

    public void setProduct(Double product) {
        this.product = product;
    }

    public Double getQuotient() {
        return quotient;
    }

    public void setQuotient(Double quotient) {
        this.quotient = quotient;
    }

    private Integer a;
    private Integer b;
    private Integer sum;
    private Integer difference;
    private Double product;
    private Double quotient;


}
