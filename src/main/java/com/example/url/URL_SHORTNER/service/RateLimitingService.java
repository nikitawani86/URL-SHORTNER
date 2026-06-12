package com.example.url.URL_SHORTNER.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RateLimitingService {

	
	private  final RedisTemplate<String, Object> redisTemplate;
	
	private static final int LIMIT = 5;
	
	public boolean isAllowed(String ipAddr) {
		String key = "rate_limit: " +ipAddr;
		
		Long count = redisTemplate.opsForValue().increment(key);
		
		if(count ==1 ) {
			redisTemplate.expire(key, Duration.ofMinutes(1));
		}
		
		return count <= LIMIT;
		
	}
}
