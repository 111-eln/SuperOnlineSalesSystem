package com.atmosware.Superonline.StockService;

import com.atmosware.Superonline.StockServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class StockServiceApplication {

    public StockServiceApplication() throws IOException {
    }

    public static void main(String[] args) {
		SpringApplication.run(StockServiceApplication.class, args);
	}
//	Server server = ServerBuilder.forPort(6565)
//			.addService(new CampaignGrpcServiceImpl())
//			.build()
//					.start();

}
