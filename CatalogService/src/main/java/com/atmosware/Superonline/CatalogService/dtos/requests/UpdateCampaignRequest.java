package com.atmosware.Superonline.CatalogService.dtos.requests;

import lombok.Data;
@Data
public class UpdateCampaignRequest {
        private String CampaignName;
        private String ContentOfCampaign;
        private String UserGroupOfCampaign;
        private int StockOfCampaign;


}
