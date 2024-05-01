package com.techprimers.grpc.client.controller;

import com.techprimers.grpc.client.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {

    final TestService memberService;

    @GetMapping("/test")
    private String getData() {
        memberService.loginTest("abc");
        return null;
    }

}
