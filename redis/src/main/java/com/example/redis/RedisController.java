package com.example.redis;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/redis")
public class RedisController extends RedisAbstractController {

    @GetMapping(value = "/find/{haskey}")
    public ResponseEntity<Response> getDataByHaskey(@PathVariable String haskey) {

        return service.getDataByHaskey(haskey);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<Response> getAllData() {
        return service.getAllData();
    }

}