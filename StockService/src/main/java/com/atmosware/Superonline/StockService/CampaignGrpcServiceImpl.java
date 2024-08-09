package com.atmosware.Superonline.StockService;

import com.atmosware.Superonline.CampaignRequest;
import com.atmosware.Superonline.CampaignResponse;
import com.atmosware.Superonline.StockServiceGrpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class CampaignGrpcServiceImpl extends StockServiceGrpc.StockServiceImplBase {
    private static final Logger logger= LoggerFactory.getLogger(CampaignGrpcServiceImpl.class);
    private CampaignStockRepository repository;

    @Override
    public void addCampaign(CampaignRequest request,
                            io.grpc.stub.StreamObserver<CampaignResponse> responseObserver) {
        logger.info("istek alindi"+" "+request.getCampaignName());
        String campaignName=request.getCampaignName();
        int stock=request.getCampaignStock();
        CampaignStock campaignStock=new CampaignStock(1,campaignName,stock);
        repository.save(campaignStock);
        CampaignResponse response=CampaignResponse.newBuilder()
                .setMessage("servera basariyla eklendi")
                .build();


        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}
