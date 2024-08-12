package com.atmosware.SuperOnline.StockServer;


import com.atmosware.SuperOnline.commonPackage.CommonCampaignStock;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();

        // Belirli sınıfların izinli olduğu bir harita oluşturun
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("com.atmosware.SuperOnline.commonPackage.CommonCampaignStock", CommonCampaignStock.class);

        javaTypeMapper.setIdClassMapping(idClassMapping);
        javaTypeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);

        converter.setJavaTypeMapper(javaTypeMapper);
        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;
    }

    @Bean
    public Queue productQueue() {
        return new Queue("catalogStockQueue", true);
    }
}
