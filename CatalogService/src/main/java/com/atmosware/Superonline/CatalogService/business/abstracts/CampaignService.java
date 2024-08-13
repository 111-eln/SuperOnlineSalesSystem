package com.atmosware.Superonline.CatalogService.business.abstracts;

import com.atmosware.Superonline.CatalogService.dtos.requests.CreateCampaignRequest;
import com.atmosware.Superonline.CatalogService.dtos.requests.DeleteCampaignRequest;
import com.atmosware.Superonline.CatalogService.dtos.requests.UpdateCampaignRequest;
import com.atmosware.Superonline.CatalogService.dtos.responses.CreateCampaignResponse;
import com.atmosware.Superonline.CatalogService.dtos.responses.DeleteCampaignResponse;
import com.atmosware.Superonline.CatalogService.dtos.responses.GetAllCampaignResponse;
import com.atmosware.Superonline.CatalogService.dtos.responses.UpdateCampaignResponse;

import java.util.List;

public interface CampaignService {
    CreateCampaignResponse addCampaign(CreateCampaignRequest request);
    DeleteCampaignResponse deleteCampaign(DeleteCampaignRequest request);
    UpdateCampaignResponse updateCampaign(UpdateCampaignRequest request);
    List<GetAllCampaignResponse> getAllCampaign();

}
