package com.mlms.controller;

import com.mlms.dtos.OrderDTO;
import com.mlms.dtos.OrderResponseDTO;
import com.mlms.dtos.OrderStatusDTO;
import com.mlms.entities.Order;
import com.mlms.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
            OrderResponseDTO orderResponseDTO = orderService.createOrderAndInvoiceForPatient(orderDTO);
            return ResponseEntity.ok(orderResponseDTO);
        } catch (Exception e) {

            throw new RuntimeException("Error in creating order");
        }
    }

    @GetMapping("/pending")
    public List<OrderStatusDTO> getPendingOrders() {
        return orderService.getPendingOrders();
    }


}
