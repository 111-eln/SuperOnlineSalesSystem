package com.atmosware.Superonline.CatalogService.api;

import com.atmosware.Superonline.CatalogService.business.abstracts.CampaignService;
import com.atmosware.Superonline.CatalogService.dtos.requests.CreateCampaignRequest;
import com.atmosware.Superonline.CatalogService.dtos.responses.CreateCampaignResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/campaigns")
@AllArgsConstructor
public class CampaignController {
    public CampaignService campaignService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCampaignResponse add(@RequestBody CreateCampaignRequest request){

          return campaignService.addCampaign(request);
    }
}
