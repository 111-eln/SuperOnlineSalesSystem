package com.atmosware.Superonline.CatalogService.mapper;

import com.atmosware.Superonline.CatalogService.dtos.requests.CreateCampaignRequest;
import com.atmosware.Superonline.CatalogService.dtos.requests.DeleteCampaignRequest;
import com.atmosware.Superonline.CatalogService.dtos.requests.UpdateCampaignRequest;
import com.atmosware.Superonline.CatalogService.dtos.responses.CreateCampaignResponse;
import com.atmosware.Superonline.CatalogService.dtos.responses.DeleteCampaignResponse;
import com.atmosware.Superonline.CatalogService.dtos.responses.GetAllCampaignResponse;
import com.atmosware.Superonline.CatalogService.dtos.responses.UpdateCampaignResponse;
import com.atmosware.Superonline.CatalogService.entities.Campaign;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;


//@Mapper(componentModel = "spring")
public interface CampaignMapper {
    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);
    CreateCampaignResponse campaignToCreateCampaignResponse(Campaign campaign);
//    DeleteCampaignResponse campaignToDeleteCampaignResponse(Campaign campaign);
//    UpdateCampaignResponse campaignToUpdateCampaignResponse(Campaign campaign);
//    GetAllCampaignResponse campaignToGetAllCampaignResponse(Campaign campaign);
    Campaign createCampaignRequestToCampaign(CreateCampaignRequest request);
//    Campaign deleteCampaignRequestToCampaign(DeleteCampaignRequest request);
//    Campaign updateCampaignRequestToCampaign(UpdateCampaignRequest request);


}
