package com.example.redis;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedisDto {
    String key;
    Object value;
}