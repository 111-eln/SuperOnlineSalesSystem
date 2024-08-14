package com.atmosware.SuperOnline.OrderService.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymenetRequest {
    private String paymentStatement;
}
