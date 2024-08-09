package com.atmosware.Superonline.StockService;

import com.atmosware.Superonline.StockRequest;
import com.atmosware.Superonline.StockResponse;
import com.atmosware.Superonline.StockServiceGrpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
@RequiredArgsConstructor
public class CampaignGrpcServiceImpl extends StockServiceGrpc.StockServiceImplBase {
    public static final Logger logger= LoggerFactory.getLogger(CampaignGrpcServiceImpl.class);
    public CampaignStockRepository repository;

    @Override
    public void addCampaign(StockRequest request,
                            StreamObserver<StockResponse> responseObserver) {
        logger.info("istek alindi"+" "+request.getStockNumber());
        String campaignName=request.getStockName();
        int stock=request.getStockNumber();
        CampaignStock campaignStock=new CampaignStock(1,campaignName,stock);
        repository.save(campaignStock);
        StockResponse response=StockResponse.newBuilder()
                .setMessage("servera basariyla eklendi")
                .build();


        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}
