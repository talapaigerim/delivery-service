package com.example.deliveryservice.service;

import com.example.deliveryservice.dto.DeliveryRequest;
import com.example.deliveryservice.dto.DeliveryResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DeliveryService {

    private static final long DUP_WINDOW_MS = 2000;


    private final Map<Long, String> statuses = new ConcurrentHashMap<>();

    // cacheEntry
    private final Map<String, CacheEntry> dedupeCache = new ConcurrentHashMap<>();

    public Mono<DeliveryResponse> create(DeliveryRequest request) {
        long now = System.currentTimeMillis();

        String status = request.getStatus() == null ? "CREATED" : request.getStatus();


        String key = request.getProductId() + "|" + request.getAddress() + "|" + status;

        CacheEntry prev = dedupeCache.get(key);
        if (prev != null && (now - prev.createdAtMs) <= DUP_WINDOW_MS) {

            return Mono.just(new DeliveryResponse(
                    true,
                    "Duplicate delivery request (within 2 seconds)",
                    prev.productId,
                    prev.status
            ));
        }


        statuses.put(request.getProductId(), status);

        String message = "Delivery created for productId=" + request.getProductId()
                + ", address=" + request.getAddress()
                + ", status=" + status;


        dedupeCache.put(key, new CacheEntry(now, request.getProductId(), status));


        if (prev != null && (now - prev.createdAtMs) > DUP_WINDOW_MS) {
            dedupeCache.remove(key);
        }

        return Mono.just(new DeliveryResponse(
                false,
                message,
                request.getProductId(),
                status
        ));
    }

    public Mono<String> getStatus(Long productId) {
        return Mono.just(statuses.getOrDefault(productId, "NOT_FOUND"));
    }

    private static class CacheEntry {
        final long createdAtMs;
        final Long productId;
        final String status;

        CacheEntry(long createdAtMs, Long productId, String status) {
            this.createdAtMs = createdAtMs;
            this.productId = productId;
            this.status = status;
        }
    }
}