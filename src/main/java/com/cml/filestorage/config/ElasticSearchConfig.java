package com.cml.filestorage.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.cml.filestorage.repository")
@ComponentScan(basePackages = { "com.cml.filestorage.service" })
public class ElasticSearchConfig {
    @Value("${elastic.port}")
    private String elasticHostAndPort;

    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration
                = ClientConfiguration.builder()
                .connectedTo(elasticHostAndPort)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }
}
