package com.atmosware.Superonline.CatalogService.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCampaignResponse {
    private String CampaignName;
    private String ContentOfCampaign;
    private String UserGroupOfCampaign;
    private int StockOfCampaign;

}