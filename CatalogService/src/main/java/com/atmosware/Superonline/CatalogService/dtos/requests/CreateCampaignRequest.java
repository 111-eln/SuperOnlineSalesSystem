package com.atmosware.Superonline.CatalogService.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCampaignRequest {
    private String campaignName;
    private String contentOfCampaign;
    private String userGroupOfCampaign;
    private int stockOfCampaign;

}
