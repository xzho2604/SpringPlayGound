package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.service.GraphQLService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class Hello {
    private final GraphQLService qlService;

    public Hello(GraphQLService qlService) {
        this.qlService = qlService;
    }

    @GetMapping("/test")
    public String testEndpoint() throws URISyntaxException {
        var car = new Car("Nissan", 5000);
        return qlService.postRequest(car);
    }

    public static void main(String[] args) throws URISyntaxException {
        var car = new Car("Nissan", 5000);
        var url = new URI("https://httpbin.org/post");
        WebClient client = WebClient.create();

        var res  = client.post()
                .uri(url)
                .body(Mono.just(car), Car.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(res);
    }
}
