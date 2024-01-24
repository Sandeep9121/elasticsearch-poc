package org.springboot.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GetEsClient {
    @Bean
    public ElasticsearchClient elasticsearchClient (){
        RestClientBuilder b= RestClient.builder(new HttpHost("localhost",9200,"https"));
        RestClientBuilder.HttpClientConfigCallback httpClientConfigCallback =new HttpClientConfigImpl();
        b.setHttpClientConfigCallback(httpClientConfigCallback);
        RestClient rc=b.build();
        RestClientTransport restClientTransport = new RestClientTransport(rc, new JacksonJsonpMapper());
        return new ElasticsearchClient(restClientTransport);
    }
}
