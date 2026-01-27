package com.example.deliveryservice.dto;

public class DeliveryRequest {
    private Long productId;
    private String address;
    private String status;
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}