package com.example.deliveryservice.controller;

import com.example.deliveryservice.dto.DeliveryRequest;
import com.example.deliveryservice.dto.DeliveryResponse;
import com.example.deliveryservice.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService service;

    public DeliveryController(DeliveryService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<ResponseEntity<DeliveryResponse>> create(@RequestBody DeliveryRequest request) {
        return service.create(request)
                .map(resp -> {
                    if (resp.isDuplicate()) {
                        return ResponseEntity.status(HttpStatus.CONFLICT).body(resp); // 409
                    }
                    return ResponseEntity.status(HttpStatus.CREATED).body(resp); // 201
                });
    }

    @GetMapping("/status/{productId}")
    public Mono<String> status(@PathVariable Long productId) {
        return service.getStatus(productId);
    }
}