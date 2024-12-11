package com.practice.sub.controller;

import com.practice.dto.CustomResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubServerController {

    // GET localhost:9090/sub
    //  "Message from SubServer"
    @GetMapping("/sub")
    public CustomResponse subEndpoint() {
        return new CustomResponse("Message from SubServer");
    }
}
