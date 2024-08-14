package com.atmosware.SuperOnline.OrderService.mapper;

import com.atmosware.SuperOnline.OrderService.dtos.requests.CreateOrderRequest;
import com.atmosware.SuperOnline.OrderService.dtos.responses.CreateOrderResponse;
import com.atmosware.SuperOnline.OrderService.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    CreateOrderResponse orderToCreateOrderResponse(Order order);
    Order createOrderRequestToOrder(CreateOrderRequest request);



}

