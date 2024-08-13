package com.atmosware.SuperOnline.StockServer.dataaccess;

import com.atmosware.SuperOnline.StockServer.entity.CampaignStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignStockRepository extends JpaRepository<CampaignStock,Integer> {
    CampaignStock findByCampaignName(String campaignName);
}

