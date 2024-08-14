package com.atmosware.Superonline.PaymentService;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="payments")
public class CardInfo {
        @Id
        @GeneratedValue
        public int Id;

        public String cardName;
        public String cardNumber;
        public String expirationDate;
        public int ccv;

}
