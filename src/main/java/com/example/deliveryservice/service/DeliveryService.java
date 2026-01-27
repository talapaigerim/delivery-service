package com.example.deliveryservice.service;

import com.example.deliveryservice.dto.DeliveryRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DeliveryService {

    // productId -> status
    private final Map<Long, String> statuses = new ConcurrentHashMap<>();

    public Mono<String> create(DeliveryRequest request) {
        // сохраняем статус (если в реквесте нет статуса — ставим дефолт)
        String status = request.getStatus() == null ? "CREATED" : request.getStatus();
        statuses.put(request.getProductId(), status);

        return Mono.just(
                "Delivery created for productId=" + request.getProductId()
                        + ", address=" + request.getAddress()
                        + ", status=" + status
        );
    }

    public Mono<String> getStatus(Long productId) {
        return Mono.just(statuses.getOrDefault(productId, "NOT_FOUND"));
    }
}