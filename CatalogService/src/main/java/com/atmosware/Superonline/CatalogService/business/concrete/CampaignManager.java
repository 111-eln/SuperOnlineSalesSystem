package com.atmosware.Superonline.CatalogService.business.concrete;

import com.atmosware.SuperOnline.commonPackage.CommonCampaignStock;
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

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class CampaignManager implements CampaignService {
        static Logger logger= LoggerFactory.getLogger(CampaignManager.class);

    public CampaignRepository campaignRepository;

    private  CampaignRedisRepository campaignRedisRepository;
    public final RabbitTemplate rabbitTemplate;



    public CampaignManager(CampaignRepository campaignRepository, CampaignRedisRepository campaignRedisRepository, RabbitTemplate rabbitTemplate) {
        this.campaignRepository = campaignRepository;

        this.campaignRedisRepository = campaignRedisRepository;
        this.rabbitTemplate = rabbitTemplate;
    }



    @Override
    public CreateCampaignResponse addCampaign(CreateCampaignRequest request) {


        Campaign campaign= CampaignMapper.INSTANCE.createCampaignRequestToCampaign(request);
        campaign.setCreatedDate(LocalDateTime.now());
        Campaign dbCampaign=campaignRepository.save(campaign);
        Campaign redisCampaign=campaignRedisRepository.save(campaign);
        CreateCampaignResponse response2=CampaignMapper.INSTANCE.campaignToCreateCampaignResponse(dbCampaign);
        CommonCampaignStock commonCampaignStock=new CommonCampaignStock(dbCampaign.getId(), request.getCampaignName(),request.getStockOfCampaign());
        rabbitTemplate.convertAndSend("catalogStockExchange","routing_key_examp",commonCampaignStock);
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
