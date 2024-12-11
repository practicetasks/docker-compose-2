package com.practice.main.controller;

import com.practice.dto.CustomResponse;
import jakarta.persistence.EntityManager;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainServerController {
    private String url = "http://sub-service:9090/sub";
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private EntityManager entityManager;

    // GET localhost:8080/main
    // Message from MainServer {Message from SubServer}
    @GetMapping("/main")
    public CustomResponse mainEndpoint() {
        String messageFromDb = entityManager.createQuery("select 'Message from Postgresql'", String.class)
                .getSingleResult();

        ResponseEntity<CustomResponse> response = restTemplate.getForEntity(url, CustomResponse.class);
        CustomResponse body = response.getBody();

        return new CustomResponse("Message from MainServer {%s} {%s}".formatted(body, messageFromDb));
    }
    // 1. Создать проект spring-microservices
    // 2. Создать модуль payment-service
    //    2.1 Создать класс Payment {"paid": true, "time": "2024-12-11T17:19:30"}
    //    2.2 Создать контроллер PaymentController
    //        - создайте эндпойнт GET /payments который возвращает просто новый объект Payment
    //         {"paid": true, "time": "2024-12-11T17:19:30"}

    // 3. Создать модуль order-service
    //    3.1 Создайте класс Order {"user": "Bob", "address": "Moscow City", payment: {"paid": true, "time": "2024-12-11T17:19:30"}}
    //    3.2 Создать контроллер OrderController
    //        - создайте эндпойнт POST /orders который принимает Order и присвайвает объект payment
    //          обращаясь к payment-service
    //         {"user": "Bob", "address": "Moscow City", payment: {"paid": true, "time": "2024-12-11T17:19:30"}}
    //        - создайте эндпойнт GET /orders для получения всех объектов Order



//    @PostMapping
//    public Order createOrder(@RequestBody Order order) {
//
//    }
}

