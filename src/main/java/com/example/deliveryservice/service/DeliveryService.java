package com.example.deliveryservice.service;

import com.example.deliveryservice.dto.DeliveryRequest;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    public void createDelivery(DeliveryRequest request) {
        System.out.println(
                "Delivery created. productId=" +
                        request.getProductId() +
                        ", address=" +
                        request.getAddress()
        );
    }
}
