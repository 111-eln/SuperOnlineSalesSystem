package com.atmosware.SuperOnline.OrderService.business;

import com.atmosware.SuperOnline.OrderService.dataAccess.OrderRepository;
import com.atmosware.SuperOnline.OrderService.dtos.requests.CreateOrderRequest;


import com.atmosware.SuperOnline.ProductRequest;
import com.atmosware.SuperOnline.ProductResponse;
import com.atmosware.SuperOnline.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService{
    static Logger logger= LoggerFactory.getLogger(OrderManager.class);

    public OrderRepository orderRepository;
    @Override
    public void add(CreateOrderRequest request) {
        ManagedChannel managedChannel= ManagedChannelBuilder.forAddress("localhost",50051).usePlaintext().build();
        ProductServiceGrpc.ProductServiceBlockingStub blockingStub=ProductServiceGrpc.newBlockingStub(managedChannel);
        ProductRequest request1=ProductRequest.newBuilder().setPackageNameofOrder(request.getPackageNameofOrder()).build();
        ProductResponse response=blockingStub.decreaseStockOfProduct(request1);
        logger.info("response from server"+" "+response.getStockNumberOfProduct() +"  "+response.getMessage());
        if (response.getStockNumberOfProduct()>0){
            logger.info("stock mevcut");
        }
        else{
            logger.info("stok yok");
        }

    }
}
