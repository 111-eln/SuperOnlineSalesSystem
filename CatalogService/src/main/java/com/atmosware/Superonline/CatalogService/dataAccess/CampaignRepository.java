package com.atmosware.Superonline.CatalogService.dataAccess;

import com.atmosware.Superonline.CatalogService.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign,Integer> {
    Campaign findByCampaignName(String campaignName);
}
