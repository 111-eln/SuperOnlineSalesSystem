package com.atmosware.SuperOnline.OrderService.business;

import com.atmosware.SuperOnline.OrderService.core.exceptions.types.BusinessException;
import com.atmosware.SuperOnline.OrderService.dtos.requests.PaymenetRequest;
import com.atmosware.SuperOnline.OrderService.dtos.responses.PaymentResponse;
import com.atmosware.SuperOnline.OrderService.entities.CardInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PaymentService {
    public WebClient webClient = WebClient.builder().build();
    public boolean success;

    public void sendPostRequest(CardInfo cardInfo) {
        String url = "http://localhost:9019/api/v1/payments";
        Mono<String> response = webClient.post()
                .uri(url)
                .bodyValue(cardInfo)
                .retrieve()
                .bodyToMono(String.class);

        response.subscribe(responseString -> {
            try {

                ObjectMapper objectMapper = new ObjectMapper();
                PaymentResponse responseapi = objectMapper.readValue(responseString, PaymentResponse.class);

                success = responseapi.success;
                System.out.println("Success: " + success);

                System.out.println("Message: " + responseapi.transactionId);

            } catch (Exception e) {
                System.err.println("hata: " + e.getMessage());
            }
        }, error -> {
            System.err.println("Hata olustu: " + error.getMessage());
        });
        if(!success){
            throw new BusinessException("odeme alinamadi");
        }
    }



    }











