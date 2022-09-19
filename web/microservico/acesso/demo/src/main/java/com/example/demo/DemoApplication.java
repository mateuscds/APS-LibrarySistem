
package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.reactive.function.client.WebClient;



import com.example.demo.collection.EstudanteCollection;

@SpringBootApplication
@RestController
public class DemoApplication {

	@Autowired
	public EstudanteCollection coll;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClient() {
		return WebClient.builder();
	}

	// @GetMapping("/hello")
	// public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		
	// 	// System.out.println("--------->" + coll.hashCode());
	// 	coll.loginEstudante("1234", "9999");
	// 	return String.format("Hello %s!", name);
	// }
}
            