package com.shop.auth_test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public ResponseEntity<String>demo(){
        return ResponseEntity.ok("hello Secur url");
    }

    @GetMapping("/only_admin")
    public  ResponseEntity<String>admin_only(){
        return ResponseEntity.ok("only admin");
    }
}
