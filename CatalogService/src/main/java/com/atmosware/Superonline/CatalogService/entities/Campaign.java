package com.atmosware.Superonline.CatalogService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="campaigns")
public class Campaign {
    @Id
    @GeneratedValue
    private int Id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    @Column(name = "CampaignName")
    private String campaignName;
    private String contentOfCampaign;
    private String userGroupOfCampaign;
    private int stockOfCampaign;

}
