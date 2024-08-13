package com.atmosware.SuperOnline.OrderService.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrderRequest {
    private String packageNameofOrder;
    private String orderAddress;
    private String orderDetails;
    private int customerNumber;

}
