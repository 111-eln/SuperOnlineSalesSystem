package com.atmosware.Superonline.CatalogService.dtos.responses;

import lombok.Data;

@Data
public class UpdateCampaignResponse {
    private String CampaignName;
    private String ContentOfCampaign;
    private String UserGroupOfCampaign;
    private int StockOfCampaign;
}
