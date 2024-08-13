package com.atmosware.SuperOnline.StockServer.rabbitmqlistener;

import com.atmosware.SuperOnline.StockServer.dataaccess.CampaignStockRepository;
import com.atmosware.SuperOnline.StockServer.entity.CampaignStock;
import com.atmosware.SuperOnline.commonPackage.CommonCampaignStock;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@AllArgsConstructor
public class RabbitMQListener {
    public CampaignStockRepository repository;

    @RabbitListener(queues = "catalogStockQueue")
    public void receiveMessage(CommonCampaignStock campaign) throws IOException {
        CampaignStock campaignStock=new CampaignStock(campaign.getId(),
                campaign.getCampaignName(), campaign.getCampaignStock());
        repository.save(campaignStock);

        // Ürün stoğunu güncelleme veya stokla ilgili başka işlemler burada yapılabilir
    }
}
