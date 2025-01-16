package brian.duran.puntos_de_ventas_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EntityScan( basePackages = {"brian.duran.commons_service.entity"})
@SpringBootApplication
@EnableCaching
public class PuntosDeVentasServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuntosDeVentasServicesApplication.class, args);
	}

}
