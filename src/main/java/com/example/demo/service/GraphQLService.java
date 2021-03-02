package com.example.demo.service;

import com.example.demo.model.Car;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class GraphQLService {
    private final WebClient simpleWebClient;

    public GraphQLService(WebClient simpleWebClient) {
        this.simpleWebClient = simpleWebClient;
    }

    public String postRequest(Car car ) throws URISyntaxException {
        var url = new URI("https://httpbin.org/post");
        WebClient client = WebClient.create();

        var res  = client.post()
                .uri(url)
                .body(Mono.just(car), Car.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(res);

        return res;
    }
}
