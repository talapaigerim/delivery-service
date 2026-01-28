package com.example.deliveryservice.dto;

public class DeliveryResponse {
    private boolean duplicate;
    private String message;
    private Long productId;
    private String status;

    public DeliveryResponse() {}

    public DeliveryResponse(boolean duplicate, String message, Long productId, String status) {
        this.duplicate = duplicate;
        this.message = message;
        this.productId = productId;
        this.status = status;
    }

    public boolean isDuplicate() { return duplicate; }
    public void setDuplicate(boolean duplicate) { this.duplicate = duplicate; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}