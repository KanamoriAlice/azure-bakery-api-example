package com.azuresdk.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

@SpringBootApplication
@EntityScan("com.azuresdk.model")
@EnableMongoRepositories(basePackages = "com.azuresdk.repository")
@ComponentScan({"com.azuresdk.controller","com.azuresdk.service", "com.azuresdk.config"})
public class AzuresdkApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzuresdkApplication.class, args);
	}

}
