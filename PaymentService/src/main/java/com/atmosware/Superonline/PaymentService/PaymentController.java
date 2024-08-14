package com.atmosware.Superonline.PaymentService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("api/v1/payments")

public class PaymentController  {

    public PaymentRepository repository;

    public PaymentController(PaymentRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse add(@RequestBody CardInfo request){
            repository.save(request);
            Random random = new Random();
            boolean randomBoolean = random.nextBoolean();
            boolean success = randomBoolean;
            String transactionId = "abc123";
            System.out.println("odemeler alindi");

            return new PaymentResponse(success, transactionId);
        }
//        return new PaymentResponse(true,"abs123");
    }


