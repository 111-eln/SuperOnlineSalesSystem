package com.atmosware.SuperOnline.OrderService.mapper;

import com.atmosware.SuperOnline.OrderService.dtos.requests.CreateOrderRequest;
import com.atmosware.SuperOnline.OrderService.dtos.responses.CreateOrderResponse;
import com.atmosware.SuperOnline.OrderService.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    public static Order toEntity(CreateOrderRequest request) {
        Order order = new Order();
        order.setPackageNameofOrder(request.getPackageNameofOrder());
        order.setOrderAddress(request.getOrderAddress());
        order.setOrderDetails(request.getOrderDetails());
        order.setCustomerNumber(request.getCustomerNumber());
        order.setCreatedDate(LocalDateTime.now()); // Created date'i otomatik ayarlayın
        return order;
    }

    public static CreateOrderResponse toResponse(Order order) {
        return new CreateOrderResponse(
                order.getPackageNameofOrder(),
                order.getCustomerNumber()
        );
    }
//    OrderMapper INSTANCE= Mappers.getMapper(OrderMapper.class);
//    CreateOrderResponse orderToCreateOrderResponse(Order order);
//    Order createOrderRequestToOrder(CreateOrderRequest request);
}
