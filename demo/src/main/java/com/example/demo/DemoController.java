package com.example.demo;

import jakarta.ws.rs.core.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/get-demo")
    public ResponseEntity<DemoDTO> getDemoDTO(){
        return ResponseEntity.ok(DemoDTO.of("Filip",25));

    }

}
