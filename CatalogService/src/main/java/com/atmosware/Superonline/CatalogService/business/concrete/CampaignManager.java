package com.atmosware.Superonline.CatalogService.business.concrete;

import com.atmosware.Superonline.CampaignRequest;
import com.atmosware.Superonline.CampaignResponse;
import com.atmosware.Superonline.CatalogService.business.abstracts.CampaignService;
import com.atmosware.Superonline.CatalogService.dataAccess.CampaignRedisRepository;
import com.atmosware.Superonline.CatalogService.dataAccess.CampaignRepository;
import com.atmosware.Superonline.CatalogService.dtos.requests.CreateCampaignRequest;
import com.atmosware.Superonline.CatalogService.dtos.requests.DeleteCampaignRequest;
import com.atmosware.Superonline.CatalogService.dtos.requests.UpdateCampaignRequest;
import com.atmosware.Superonline.CatalogService.dtos.responses.CreateCampaignResponse;
import com.atmosware.Superonline.CatalogService.dtos.responses.DeleteCampaignResponse;
import com.atmosware.Superonline.CatalogService.dtos.responses.GetAllCampaignResponse;
import com.atmosware.Superonline.CatalogService.dtos.responses.UpdateCampaignResponse;
import com.atmosware.Superonline.CatalogService.entities.Campaign;
import com.atmosware.Superonline.CatalogService.mapper.CampaignMapper;

import com.atmosware.Superonline.StockServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class CampaignManager implements CampaignService {
        static Logger logger= LoggerFactory.getLogger(CampaignManager.class);

    public CampaignRepository campaignRepository;
    public ManagedChannel managedChannel= ManagedChannelBuilder.forAddress("127.0.0.1",6565).usePlaintext().idleTimeout(100, TimeUnit.SECONDS) // 5 saniye içinde inaktif kalırsa bağlantıyı kapatır
        .build();

    public StockServiceGrpc.StockServiceBlockingStub blockingStub=StockServiceGrpc.newBlockingStub(managedChannel);
    private final CampaignRedisRepository campaignRedisRepository;


    public CampaignManager(CampaignRepository campaignRepository, CampaignRedisRepository campaignRedisRepository) {
        this.campaignRepository = campaignRepository;

        this.campaignRedisRepository = campaignRedisRepository;
    }



    @Override
    public CreateCampaignResponse addCampaign(CreateCampaignRequest request) {
        CampaignRequest request1 = CampaignRequest.newBuilder()
                .setCampaignName(request.getCampaignName())
                        .setCampaignStock(request.getStockOfCampaign())
                .build();

//        CampaignResponse response=blockingStub.addCampaign(request1);
        CampaignRequest requestdene=CampaignRequest.newBuilder()
                .setCampaignName("asdddd")
                .setCampaignStock(91)
                .build();
        CampaignResponse response=blockingStub.addCampaign(requestdene);



        logger.info(response.getMessage());
        Campaign campaign= CampaignMapper.INSTANCE.createCampaignRequestToCampaign(request);
        campaign.setCreatedDate(LocalDateTime.now());
        Campaign dbCampaign=campaignRepository.save(campaign);
        Campaign redisCampaign=campaignRedisRepository.save(campaign);
        CreateCampaignResponse response2=CampaignMapper.INSTANCE.campaignToCreateCampaignResponse(dbCampaign);
        return response2;
    }

//    @Override
//    public DeleteCampaignResponse deleteCampaign(DeleteCampaignRequest request) {
//        Campaign campaign=campaignRepository.findByCampaignName(request.getCampaignName());
//        campaign.setDeletedDate(LocalDateTime.now());
//        campaignRedisRepository.delete(campaign);
//        DeleteCampaignResponse response=CampaignMapper.INSTANCE.campaignToDeleteCampaignResponse(campaign);
//        return response;
//    }

//    @Override
//    public UpdateCampaignResponse updateCampaign(UpdateCampaignRequest request) {
//        Campaign campaign=campaignRepository.findByCampaignName(request.getCampaignName());
//        Campaign postgreCampaign=campaignRepository.save(campaign);
//        Campaign redisCampaign=campaignRedisRepository.save(campaign);
//        UpdateCampaignResponse response=CampaignMapper.INSTANCE.campaignToUpdateCampaignResponse(postgreCampaign);
//        return  response;
//
//    }
//
//    @Override
//    public List<GetAllCampaignResponse> getAllCampaign() {
//        List<Campaign> campaigns = (List<Campaign>) campaignRedisRepository.findAll();
//        List<Campaign> campaignsRedis = null;
//        if (campaigns.isEmpty()) {
//            List<Campaign> campaignsPostgre = campaignRepository.findAll();
//            campaignsRedis = campaignsPostgre.stream().map(campaign -> campaignRedisRepository.save(campaign)).toList();
//        }
//        List<GetAllCampaignResponse> response = campaignsRedis.stream().map(campaign -> CampaignMapper.INSTANCE.campaignToGetAllCampaignResponse(campaign)).toList();
//        return response;
//    }
}
