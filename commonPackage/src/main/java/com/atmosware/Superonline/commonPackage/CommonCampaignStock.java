package com.atmosware.SuperOnline.commonPackage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="campaignStocks")
public class CommonCampaignStock implements Serializable {
    @jakarta.persistence.Id
    @GeneratedValue
    private int Id;
    private String campaignName;
    private int campaignStock;
}
