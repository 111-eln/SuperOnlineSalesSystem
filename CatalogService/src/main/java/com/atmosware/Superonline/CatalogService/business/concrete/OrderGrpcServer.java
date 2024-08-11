package com.atmosware.Superonline.CatalogService.business.concrete;

import com.atmosware.SuperOnline.CatalogUpdatedRequest;
import com.atmosware.SuperOnline.CatalogUpdatedResponse;
import com.atmosware.SuperOnline.CatalogprotoServiceGrpc;
import com.atmosware.Superonline.CatalogService.dataAccess.CampaignRepository;
import com.atmosware.Superonline.CatalogService.entities.Campaign;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
public class OrderGrpcServer extends CatalogprotoServiceGrpc.CatalogprotoServiceImplBase {
    public CampaignRepository campaignRepository;
    @Override
    public void stockCatalogUpdate(CatalogUpdatedRequest request,
                                   StreamObserver<CatalogUpdatedResponse> responseObserver) {
        String packageName=request.getPackageNameofOrder();
        Campaign campaign=campaignRepository.findByCampaignName(packageName);
        campaignRepository.delete(campaign);
        CatalogUpdatedResponse response=CatalogUpdatedResponse.newBuilder().setMessageResponse("Product is deleted from catalogdb").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
