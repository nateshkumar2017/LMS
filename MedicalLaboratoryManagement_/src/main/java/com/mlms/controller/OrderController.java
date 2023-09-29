package com.mlms.controller;

import com.mlms.dtos.OrderDTO;
import com.mlms.dtos.OrderResponseDTO;
import com.mlms.entities.Order;
import com.mlms.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrderAndInvoice(@RequestBody OrderDTO orderDTO) {
        try {
            // Call the service method to create the order and invoice
            OrderResponseDTO orderResponseDTO = orderService.createOrderAndInvoiceForPatient(orderDTO);
            return ResponseEntity.ok(orderResponseDTO);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            throw new RuntimeException("Error in creating order");
        }
    }
}
