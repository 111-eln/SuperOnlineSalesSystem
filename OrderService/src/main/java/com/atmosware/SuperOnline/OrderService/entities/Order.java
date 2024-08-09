package com.atmosware.SuperOnline.OrderService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue
    private int Id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private String packageNameofOrder;
    private String orderAddress;
    private String orderDetails;
    private int customerNumber;
}
