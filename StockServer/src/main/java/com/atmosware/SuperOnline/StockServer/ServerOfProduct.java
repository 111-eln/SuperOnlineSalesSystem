package com.atmosware.SuperOnline.StockServer;


import com.atmosware.SuperOnline.ProductRequest;
import com.atmosware.SuperOnline.ProductResponse;
import com.atmosware.SuperOnline.ProductServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
@AllArgsConstructor

public class ServerOfProduct extends ProductServiceGrpc.ProductServiceImplBase {
//    Logger logger= LoggerFactory.getLogger(ServerOfProduct.class);
    public CampaignStockRepository repository;

    @Override
    public void decreaseStockOfProduct(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        CampaignStock campaign= repository.findByCampaignName(request.getPackageNameofOrder());
        int guncelStok=campaign.getCampaignStock()-1;
        campaign.setCampaignStock(guncelStok);
        repository.save(campaign);
        ProductResponse response=ProductResponse.newBuilder().setStockNumberOfProduct(guncelStok).setMessage("stok dusuruldu").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
