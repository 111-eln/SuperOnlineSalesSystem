package com.atmosware.SuperOnline.commonPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonCampaignStock implements Serializable {

    private int Id;
    private String campaignName;
    private int campaignStock;
}
