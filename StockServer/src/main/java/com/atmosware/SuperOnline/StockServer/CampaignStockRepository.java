package com.atmosware.SuperOnline.StockServer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignStockRepository extends JpaRepository<CampaignStock,Integer> {
    CampaignStock findByCampaignName(String campaignName);
}

