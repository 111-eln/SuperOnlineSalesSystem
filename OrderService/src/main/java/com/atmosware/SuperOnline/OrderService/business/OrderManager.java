package com.atmosware.SuperOnline.OrderService.business;

import com.atmosware.SuperOnline.*;
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
    //@Transactional
    @Override
    public void add(CreateOrderRequest createOrderRequest) {
        ManagedChannel managedChannel= ManagedChannelBuilder.forAddress("localhost",50051).usePlaintext().build();
        ProductServiceGrpc.ProductServiceBlockingStub blockingStub=ProductServiceGrpc.newBlockingStub(managedChannel);
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
        }
        else if (response.getStockNumberOfProduct()>1){
            logger.info("stock mevcut");
        }

        else{
            //TODO:burada stok bitti diye business exception fırlatilmali
            logger.info("stok yok");
        }



    }
}
