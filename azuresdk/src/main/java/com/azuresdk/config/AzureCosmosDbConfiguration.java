package com.azuresdk.config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.azure.cosmos.CosmosClientBuilder;
// import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
// import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

// @Configuration
// @EnableCosmosRepositories(basePackages = "com.azuresdk.repository")
public class AzureCosmosDbConfiguration /* extends AbstractCosmosConfiguration */ {

    // @Value("${azure.cosmosdb.uri}")
    // private String uri;

    // @Value("${azure.cosmosdb.key}")
    // private String key;

    // @Value("${azure.cosmosdb.database}")
    // private String dbName;

    // @Bean
    // public CosmosClientBuilder cosmosClientBuilder() {
    //     return new CosmosClientBuilder()
    //             .endpoint(uri)
    //             .key(key);
    // }

    // @Override
    // protected String getDatabaseName() {
    //     return dbName;
    // }
}
