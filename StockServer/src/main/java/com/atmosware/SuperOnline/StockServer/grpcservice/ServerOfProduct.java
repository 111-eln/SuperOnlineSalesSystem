package com.atmosware.SuperOnline.StockServer.grpcservice;


import com.atmosware.SuperOnline.ProductRequest;
import com.atmosware.SuperOnline.ProductResponse;
import com.atmosware.SuperOnline.ProductServiceGrpc;
//import com.atmosware.Superonline.CampaignStock;
import com.atmosware.SuperOnline.StockServer.dataaccess.CampaignStockRepository;
import com.atmosware.SuperOnline.StockServer.entity.CampaignStock;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

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
