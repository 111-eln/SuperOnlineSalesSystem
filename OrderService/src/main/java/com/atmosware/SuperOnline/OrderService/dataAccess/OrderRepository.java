package com.atmosware.SuperOnline.OrderService.dataAccess;

import com.atmosware.SuperOnline.OrderService.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
