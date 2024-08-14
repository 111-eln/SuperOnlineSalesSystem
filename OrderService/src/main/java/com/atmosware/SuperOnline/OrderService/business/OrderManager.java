package com.atmosware.SuperOnline.OrderService.business;

import com.atmosware.SuperOnline.*;
import com.atmosware.SuperOnline.OrderService.core.exceptions.types.BusinessException;
import com.atmosware.SuperOnline.OrderService.dataAccess.OrderRepository;
import com.atmosware.SuperOnline.OrderService.dtos.requests.CreateOrderRequest;


import com.atmosware.SuperOnline.OrderService.dtos.responses.CreateOrderResponse;
import com.atmosware.SuperOnline.OrderService.entities.Order;
import com.atmosware.SuperOnline.OrderService.mapper.OrderMapper;
import com.atmosware.SuperOnline.ProductRequest;
import com.atmosware.SuperOnline.ProductResponse;
import com.atmosware.SuperOnline.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service


public class OrderManager implements OrderService{
    static Logger logger= LoggerFactory.getLogger(OrderManager.class);
    public final RabbitTemplate rabbitTemplate;

    public OrderRepository orderRepository;
    public PaymentService paymentService;

    public OrderManager(RabbitTemplate rabbitTemplate, OrderRepository orderRepository, PaymentService paymentService) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }


    //@Transactional
    @Override
    public void add(CreateOrderRequest createOrderRequest) {
        Order order= OrderMapper.INSTANCE.createOrderRequestToOrder(createOrderRequest);
        order.setCreatedDate(LocalDateTime.now());
        Order dbOrder=orderRepository.save(order);
        CreateOrderResponse responseOrder=OrderMapper.INSTANCE.orderToCreateOrderResponse(dbOrder);


        ManagedChannel managedChannel= ManagedChannelBuilder.forAddress("localhost",50051).usePlaintext().build();//kanal oluşturur
        ProductServiceGrpc.ProductServiceBlockingStub blockingStub=ProductServiceGrpc.newBlockingStub(managedChannel);//istemcidir,servera yapilan çagrinin yanıtını bekler


        //katalog için
        ManagedChannel catalogManagedChannel= ManagedChannelBuilder.forAddress("localhost",50053).usePlaintext().build();
        CatalogprotoServiceGrpc.CatalogprotoServiceBlockingStub catalogBlockingStub=CatalogprotoServiceGrpc.newBlockingStub(catalogManagedChannel);
        //katalog için


        ProductRequest request=ProductRequest.newBuilder().setPackageNameofOrder(createOrderRequest.getPackageNameofOrder()).build();
        ProductResponse response=blockingStub.decreaseStockOfProduct(request);
        logger.info("response from server"+" "+response.getStockNumberOfProduct() +"  "+response.getMessage());
        if (response.getStockNumberOfProduct()==0){
            CatalogUpdatedRequest catalogUpdatedRequest= CatalogUpdatedRequest.newBuilder().setPackageNameofOrder(createOrderRequest.getPackageNameofOrder()).build();
            CatalogUpdatedResponse catalogUpdatedResponse=catalogBlockingStub.stockCatalogUpdate(catalogUpdatedRequest);
            logger.info(catalogUpdatedResponse.getMessageResponse());
            paymentService.sendPostRequest(createOrderRequest.getCardInfo());
            rabbitTemplate.convertAndSend("orderNotificationExchange","routing_key_orNot","Order process is not done");

        }
        else if (response.getStockNumberOfProduct()>1){
            logger.info("stock mevcut");
            paymentService.sendPostRequest(createOrderRequest.getCardInfo());
            rabbitTemplate.convertAndSend("orderNotificationExchange","routing_key_orNot","order process is done");

        }

        else{
            throw new BusinessException("Stok yok");

        }

//        paymentService.sendPostRequest(createOrderRequest.getCardInfo());



    }
}
