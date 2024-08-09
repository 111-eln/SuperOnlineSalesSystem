package com.atmosware.Superonline.StockService;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="campaignStocks")
public class CampaignStock {
    @Id
    @GeneratedValue
    private int Id;
    @Column(name = "campaignName")
    private String campaignName;
    @Column(name = "campaignStock")
    private int campaignStock;
}
