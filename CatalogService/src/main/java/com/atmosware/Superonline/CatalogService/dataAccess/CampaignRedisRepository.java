package com.atmosware.Superonline.CatalogService.dataAccess;

import com.atmosware.Superonline.CatalogService.entities.Campaign;
import org.springframework.data.repository.CrudRepository;

public interface CampaignRedisRepository extends CrudRepository<Campaign,Integer> {
}
