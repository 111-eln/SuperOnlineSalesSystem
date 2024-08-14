package com.atmosware.Superonline.PaymentService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<CardInfo,Integer> {
}
