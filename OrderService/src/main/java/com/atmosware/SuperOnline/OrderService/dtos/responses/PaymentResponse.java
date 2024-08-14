package com.atmosware.SuperOnline.OrderService.dtos.responses;

import lombok.Data;

@Data
public class PaymentResponse {
    public boolean success;
    public String transactionId;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentResponse() {
    }

    public PaymentResponse(boolean success, String transactionId) {
        this.success = success;
        this.transactionId = transactionId;
    }

    // Getter'lar
}