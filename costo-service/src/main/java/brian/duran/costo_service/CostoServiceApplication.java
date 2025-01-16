package brian.duran.costo_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@EntityScan( basePackages = {"brian.duran.commons_service.entity"})
@SpringBootApplication
public class CostoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CostoServiceApplication.class, args);
	}

}
