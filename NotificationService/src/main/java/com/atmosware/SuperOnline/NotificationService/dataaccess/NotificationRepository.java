package com.atmosware.SuperOnline.NotificationService.dataaccess;

import com.atmosware.SuperOnline.NotificationService.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
