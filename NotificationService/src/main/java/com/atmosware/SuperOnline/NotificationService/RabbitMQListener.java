package com.atmosware.SuperOnline.NotificationService;

import com.atmosware.SuperOnline.NotificationService.dataaccess.NotificationRepository;
import com.atmosware.SuperOnline.NotificationService.entity.Notification;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class RabbitMQListener {
    private NotificationRepository repository;


    @RabbitListener(queues = "orderNotificationQueue")
    public void receiveMessage(String log) throws IOException {
        Notification notification=new Notification();
        notification.setLog(log);
        notification.setCreatedDate(LocalDateTime.now());
        repository.save(notification);

        // Ürün stoğunu güncelleme veya stokla ilgili başka işlemler burada yapılabilir
    }
}
