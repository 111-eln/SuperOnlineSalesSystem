package com.atmosware.SuperOnline.OrderService.business;

import com.atmosware.SuperOnline.OrderService.core.exceptions.types.BusinessException;
import com.atmosware.SuperOnline.OrderService.dtos.requests.PaymenetRequest;
import com.atmosware.SuperOnline.OrderService.entities.CardInfo;

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

    public void sendPostRequest(CardInfo cardInfo) {
        String url = "http://localhost:9019/api/v1/payments";  // Hedef mikroservis URL'si

        // Gönderilecek veriyi hazırlayın
//        CardInfo requestData = new CardInfo();

        // POST isteğini gönder
        Mono<String> response = webClient.post()
                .uri(url)
                .bodyValue(cardInfo)
                .retrieve()
                .bodyToMono(String.class);

        // Yanıtı al
       System.out.println(response);


    }











   // private  WebClient webClient;



//    public PaymentService(@Value("${payment.service.url}") String paymentServiceUrl) {
//        this.webClient = WebClient.builder()
//                .baseUrl(paymentServiceUrl)
//                .build();
//    }
//
//    public void processPayment(CardInfo cardDetails) {
//        String query = """
//            mutation {
//                processPayment(cardNumber: "%s", cardHolderName: "%s", expirationDate: "%s", cvv: "%s") {
//                    success
//                    transactionId
//                }
//            }
//        """.formatted(
//                cardDetails.cardNumber,
//                cardDetails.cardName,
//                cardDetails.expirationDate,
//                cardDetails.ccv
//        );
//
//        webClient.post()
//                .uri("/graphql")
//                .bodyValue(query)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
}

