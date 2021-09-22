package com.example.ordermanagement.xport.client;

import com.example.ordermanagement.domain.valueobjects.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class ArticleClient {

    /*
    Ova e kreirano so cel da proverime dali raboti testot napisan vo ovoj modul, za soodvetno da
    proverime dali dobro se listaat site articles elementi od articles modulot.
     */

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public ArticleClient(@Value("${app.articles.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Article> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/article").build().toUri(), HttpMethod.GET,null,
                    new ParameterizedTypeReference<List<Article>>() {
            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }






}
