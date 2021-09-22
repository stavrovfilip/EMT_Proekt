package com.example.ordermanagement.xport.client.rest;

import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderId;
import com.example.ordermanagement.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-management")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class OrderController {

    /*
    Rest Controller za naracki, kade mozeme da gi izlistame site naracki i da pronajdeme konkretna naracka
     */

    private final OrderService orderService;

    @GetMapping
    public List<Order> findAll (){
        return this.orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable OrderId id) {
        return this.orderService.findById(id)
                .map(order -> ResponseEntity.ok().body(order))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
