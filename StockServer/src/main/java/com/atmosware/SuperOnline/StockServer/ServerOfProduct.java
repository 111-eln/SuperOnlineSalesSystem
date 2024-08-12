package com.atmosware.SuperOnline.StockServer;


import com.atmosware.SuperOnline.ProductRequest;
import com.atmosware.SuperOnline.ProductResponse;
import com.atmosware.SuperOnline.ProductServiceGrpc;
//import com.atmosware.Superonline.CampaignStock;
import com.atmosware.SuperOnline.commonPackage.CommonCampaignStock;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@GrpcService
@AllArgsConstructor

public class ServerOfProduct extends ProductServiceGrpc.ProductServiceImplBase {
//    Logger logger= LoggerFactory.getLogger(ServerOfProduct.class);
    public CampaignStockRepository repository;

    @Override
    public void decreaseStockOfProduct(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        com.atmosware.SuperOnline.StockServer.CampaignStock campaign= repository.findByCampaignName(request.getPackageNameofOrder());
        int guncelStok=campaign.getCampaignStock()-1;
        campaign.setCampaignStock(guncelStok);
        repository.save(campaign);
        ProductResponse response=ProductResponse.newBuilder().setStockNumberOfProduct(guncelStok).setMessage("stok dusuruldu").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @RabbitListener(queues = "catalogStockQueue")
    public void receiveMessage(CommonCampaignStock campaign) {
        CampaignStock campaignStock=new CampaignStock(campaign.getId(),
                campaign.getCampaignName(), campaign.getCampaignStock());
        repository.save(campaignStock);

        // Ürün stoğunu güncelleme veya stokla ilgili başka işlemler burada yapılabilir
    }

}
