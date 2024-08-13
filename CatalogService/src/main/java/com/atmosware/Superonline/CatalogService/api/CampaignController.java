package com.atmosware.Superonline.CatalogService.api;

import com.atmosware.Superonline.CatalogService.business.abstracts.CampaignService;
import com.atmosware.Superonline.CatalogService.dtos.requests.CreateCampaignRequest;
import com.atmosware.Superonline.CatalogService.dtos.requests.DeleteCampaignRequest;
import com.atmosware.Superonline.CatalogService.dtos.requests.UpdateCampaignRequest;
import com.atmosware.Superonline.CatalogService.dtos.responses.CreateCampaignResponse;
import com.atmosware.Superonline.CatalogService.dtos.responses.GetAllCampaignResponse;
import com.atmosware.Superonline.CatalogService.dtos.responses.UpdateCampaignResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCampaign(@RequestBody DeleteCampaignRequest request) {
        campaignService.deleteCampaign(request);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdateCampaignResponse updateCampaign(@PathVariable Long id, @RequestBody UpdateCampaignRequest request) {
        return campaignService.updateCampaign(request);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCampaignResponse> getAllCampaigns() {
        return campaignService.getAllCampaign();
    }

}
