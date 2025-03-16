package com.example.redis;

import com.google.gson.Gson;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepo {

    @Value("${spring.redis.timeout}")
    private int redisTimeOut;

    @Autowired
    RedisTemplate redisTemplate;

    public Object saveOrUpdate(String haskey, Object model) {
        redisTemplate.opsForValue().set(haskey, new Gson().toJson(model));
        redisTemplate.expire(haskey, redisTimeOut, TimeUnit.MINUTES);
        return model;
    }

    public List<RedisDto> getAllData() {
        return null;
    }

    public RedisDto getDataByHaskeyForString(String haskey) {
        try {

            String value = (String) redisTemplate.opsForValue().get(haskey);
            if (value != null) {
                return RedisDto.builder()
                        .key(haskey)
                        .value(value)
                        .build();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public RedisDto getDataByHaskeyForEntity(String haskey, Class<?> classOfT) {
        try {
            String value = (String) redisTemplate.opsForValue().get(haskey);
            if (value != null) {

                return RedisDto.builder()
                        .key(haskey)
                        .value(new Gson().fromJson(redisTemplate.opsForValue().get(haskey).toString(), classOfT))
                        .build();
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    public boolean deleteDataByHaskey(String haskey) {
        redisTemplate.opsForValue().getAndDelete(haskey);
        return true;
    }
}
