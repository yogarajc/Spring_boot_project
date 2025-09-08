package com.example.demo.BO;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.ResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OnlineShopBO {
    HashMap<String,Object> ProductLedger = new HashMap<>();
    HashMap<String,Object> OrdersLedger = new HashMap<>();
    public ResponseDTO addProduct(ProductDTO productDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            if (ProductLedger.containsKey(productDTO.getProductId())){
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Product ID already present");
            }
            else if (productDTO.getProductId().isEmpty()){
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Product ID field is empty");
            }
            else if (productDTO.getName().isEmpty()){
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Product Name field is empty");
            }
            else if (productDTO.getPrice() <= 0){
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Product price field is empty");
            }
            else if (productDTO.getStock() <=0 ){
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Product Stock field is empty");
            } else {
                ProductLedger.put(productDTO.getProductId(),productDTO);
                responseDTO.setStatus("Success");
                responseDTO.setMessage("Product Added Successfully");
            }
            return responseDTO;
        } catch (Exception exc){
            responseDTO.setStatus("Exception");
            responseDTO.setMessage(exc.getMessage());
            return responseDTO;
        }
    }

    public Object getallProducts(){
        ResponseDTO responseDTO = new ResponseDTO();
        List<Object> ProductsList = new ArrayList<>();
        if (ProductLedger.isEmpty()){
            responseDTO.setStatus("Failure");
            responseDTO.setMessage("No Products available");
            return responseDTO;
        }
        for (Map.Entry<String,Object> productsList : ProductLedger.entrySet()){
            ProductsList.add(productsList.getValue());
        }
        return ProductsList;
    }

    public ResponseDTO placeOrder(OrderDTO orderDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        ProductDTO productDTO = new ProductDTO();

        try{
            if (!ProductLedger.containsKey(orderDTO.getProductId())){
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("product ID is not found");
            }
            productDTO = (ProductDTO) ProductLedger.get(orderDTO.getProductId());
            if (productDTO.getStock() <= 0) {
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Stock out for product ID "+orderDTO.getProductId());
            } else if (productDTO.getStock() < orderDTO.getQuantity()){
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Ordered quantity was higher than stock");
                return responseDTO;
            } else {
                productDTO.setStock(productDTO.getStock() - orderDTO.getQuantity());
                orderDTO.setOrderId(OrdersLedger.isEmpty() ? 1 : OrdersLedger.size() + 1);
                orderDTO.setTotalPrice(orderDTO.getQuantity() * productDTO.getPrice());
                OrdersLedger.put(orderDTO.getOrderId().toString(), orderDTO);
                responseDTO.setStatus("Success");
                responseDTO.setMessage("Order placed Successfully for product ID "+orderDTO.getProductId());
            }
        } catch (Exception exc){
            responseDTO.setStatus("Exception");
            responseDTO.setMessage(exc.getMessage());
        }
        return responseDTO;
    }

    public Object getAllOrders(){
        ResponseDTO responseDTO = new ResponseDTO();
        List<Object> OrdersList = new ArrayList<>();
        if (OrdersLedger.isEmpty()){
            responseDTO.setStatus("Failure");
            responseDTO.setMessage("No Orders are placed");
            return responseDTO;
        }
        for (Map.Entry<String, Object> ordersList : OrdersLedger.entrySet()){
            OrdersList.add(ordersList.getValue());
        }
        return OrdersList;
    }

}
