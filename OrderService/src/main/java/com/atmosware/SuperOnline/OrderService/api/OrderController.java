package com.atmosware.SuperOnline.OrderService.api;

import com.atmosware.SuperOnline.OrderService.business.OrderService;
import com.atmosware.SuperOnline.OrderService.dtos.requests.CreateOrderRequest;
import com.atmosware.SuperOnline.OrderService.dtos.responses.CreateOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {
    public OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody CreateOrderRequest request){
         orderService.add(request);
    }
}
