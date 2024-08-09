package com.atmosware.SuperOnline.OrderService.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrderResponse {
    private String packageNameofOrder;
    private int customerNumber;
}
