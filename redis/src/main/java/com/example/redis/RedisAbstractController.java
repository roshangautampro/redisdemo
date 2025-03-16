package com.example.redis;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class RedisAbstractController {

    @Autowired
    public RedisServerService service;

    @PostMapping(value = "/saveupdate")
    public ResponseEntity<Response> saveOrUpdateData(@RequestBody RedisDto redisDto) {
        return service.saveOrUpdateData(redisDto);
    }

    @DeleteMapping(value = "/delete/{haskey}")
    public ResponseEntity<Response> deleteDataByHaskey(@PathVariable String haskey) {

        return service.deleteDataByHaskey(haskey);
    }
}