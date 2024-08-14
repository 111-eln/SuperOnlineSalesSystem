package com.atmosware.Superonline.PaymentService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payments")


public class PaymentController {
    public PaymentController() {
    }

    //    public OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse add(@RequestBody CardInfo request){
        return new PaymentResponse(true,"abs123");
    }
}

