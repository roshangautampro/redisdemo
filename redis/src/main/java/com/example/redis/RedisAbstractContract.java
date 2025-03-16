package com.example.redis;

import org.springframework.http.ResponseEntity;

public interface RedisAbstractContract {
    public abstract ResponseEntity getDataByHaskey(String haskey);
}