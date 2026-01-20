package com.example.deliveryservice.service;

import com.example.deliveryservice.dto.DeliveryRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeliveryService {

    public Mono<String> create(DeliveryRequest request) {

        System.out.println(
                "DELIVERY SERVICE CALLED productId=" +
                        request.getProductId() +
                        ", address=" +
                        request.getAddress()
        );

        return Mono.just(
                "Delivery created for productId=" +
                        request.getProductId() +
                        ", address=" +
                        request.getAddress()
        );
    }
}