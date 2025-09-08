package com.example.demo.controller;

import com.example.demo.BO.OnlineShopBO;
import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OnlineShopController {

    private static final Logger log = LoggerFactory.getLogger(OnlineShopController.class);
    @Autowired
    OnlineShopBO onlineShopBO;

    @PostMapping("/products")
    public ResponseDTO addproduct(@RequestBody ProductDTO productDTO){
        log.info("Add Product API initiated");
        return onlineShopBO.addProduct(productDTO);
    }

    @GetMapping("/products")
    public Object getAllProducts(){
        log.info("GetAll products API inititated");
        return onlineShopBO.getallProducts();
    }

    @PostMapping("/orders")
    public ResponseDTO placeOrder(@RequestBody OrderDTO orderDTO){
        log.info("PlaceOrder API Initiated");
        return onlineShopBO.placeOrder(orderDTO);
    }

    @GetMapping("/orders")
    public Object getAllOrders(){
        log.info("GetAll orders");
        return onlineShopBO.getAllOrders();
    }

}
