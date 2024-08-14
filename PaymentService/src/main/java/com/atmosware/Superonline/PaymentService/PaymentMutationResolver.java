package com.atmosware.Superonline.PaymentService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/payments")
public class PaymentMutationResolver {//implements GraphQLMutationResolver {

    public PaymentResponse processPayment(String cardNumber, String cardHolderName, String expirationDate, String cvv) {
        // Kart bilgilerini işleyin ve ödeme yapın
        boolean success = true; // Ödeme işlemi başarılı mı?
        String transactionId = "abc123"; // Oluşan işlem ID'si
        System.out.println("odemeler alindi");

        return new PaymentResponse(success, transactionId);
    }
}
