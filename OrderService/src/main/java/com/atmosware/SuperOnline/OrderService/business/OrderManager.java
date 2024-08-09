package com.atmosware.SuperOnline.OrderService.business;

import com.atmosware.SuperOnline.OrderService.dataAccess.OrderRepository;
import com.atmosware.SuperOnline.OrderService.dtos.requests.CreateOrderRequest;
import com.atmosware.SuperOnline.OrderService.dtos.responses.CreateOrderResponse;
import com.atmosware.SuperOnline.OrderService.entities.Order;
import com.atmosware.SuperOnline.OrderService.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService{
    public OrderRepository orderRepository;
    @Override
    public CreateOrderResponse add(CreateOrderRequest request) {
        Order order= OrderMapper.toEntity(request);//.INSTANCE.createOrderRequestToOrder(request);
        Order dbOrder=orderRepository.save(order);
        CreateOrderResponse response= OrderMapper.toResponse(dbOrder);//.INSTANCE.orderToCreateOrderResponse(dbOrder);
        return response;
    }
}
