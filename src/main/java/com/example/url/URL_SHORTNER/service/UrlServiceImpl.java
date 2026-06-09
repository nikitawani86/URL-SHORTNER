package com.example.url.URL_SHORTNER.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.url.URL_SHORTNER.DTO.StatResponseDTO;
import com.example.url.URL_SHORTNER.DTO.UrlRequest;
import com.example.url.URL_SHORTNER.Exception.URLNotFoundException;
import com.example.url.URL_SHORTNER.Exception.UrlExpiredException;
import com.example.url.URL_SHORTNER.entity.UrlShort;
import com.example.url.URL_SHORTNER.repository.urlShort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl  implements UrlService{
	
	private static final Logger Log  = LoggerFactory.getLogger(UrlServiceImpl.class);
	
	private final urlShort repo;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Override
	public String generateShortUrl(UrlRequest request) {
		
		// TODO Auto-generated method stub
		UrlShort url = UrlShort.builder()
					.originalUrl(request.getOriginalUrl())
					.build();
		
		//Save first to get ID
		UrlShort saved = repo.save(url);
		//generate short code
		String shortcode ;
		if(request.getAlias()!= null && !request.getAlias().isBlank()) {
			shortcode = request.getAlias();
		}else {
			shortcode = Base62util.encode(saved.getId());
		}
		Log.info("Generated Short Code: {}"+shortcode);
		saved.setClickCount(0L);
		saved.setShortCode(shortcode);
		saved.setExpireAt(request.getExpireAt());
		repo.save(saved);
		return "https://localhost:8080/"+shortcode;
		
	}

	@Override
	public String redirect(String shortcode) {
		// TODO Auto-generated method stub
		
		UrlShort url = repo.findByShortCode(shortcode)
				.orElseThrow(() -> new URLNotFoundException("Short URL Not Found."));
		Log.info("Click Count: {}" , url.getClickCount());
		if(url.getExpireAt() != null && LocalDateTime.now() .isAfter(url.getExpireAt())) {
			throw new UrlExpiredException("URL has been expired");
		}
		System.out.println("Click Count  : "+ url.getClickCount());
		 redisTemplate.opsForValue().set(shortcode,url.getOriginalUrl() , Duration.ofMinutes(10));
		
		url.setClickCount(url.getClickCount()+1);
		url.setLastAccessedAt(LocalDateTime.now());
		repo.save(url);
		return url.getOriginalUrl();
				
	}

	@Override
	public StatResponseDTO getStatus(String shortcode) {
		// TODO Auto-generated method stub
		
		UrlShort url = repo.findByShortCode(shortcode).orElseThrow(() -> new URLNotFoundException("Short Url Not Found"));
	StatResponseDTO response = new StatResponseDTO();
response.setShortCode(url.getShortCode());
response.setOriginalUrl(url.getOriginalUrl());
response.setClickCount(url.getClickCount());
response.setExpireAt(url.getExpireAt());
response.setCreatedAt(url.getCreatedAt());
response.setExpireAt(url.getExpireAt());
response.setLastAccessedTime(url.getLastAccessedAt());
	
		return response;
	}

	
}
