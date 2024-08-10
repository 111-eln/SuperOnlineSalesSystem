package com.atmosware.SuperOnline.OrderService.business;

import com.atmosware.SuperOnline.OrderService.dtos.requests.CreateOrderRequest;
import com.atmosware.SuperOnline.OrderService.dtos.responses.CreateOrderResponse;

public interface OrderService {
    void add(CreateOrderRequest request);
}
