package com.example.url.URL_SHORTNER.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.url.URL_SHORTNER.Exception.URLNotFoundException;
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
	public String generateShortUrl(String originalUrl) {
		
		// TODO Auto-generated method stub
		UrlShort url = UrlShort.builder()
					.originalUrl(originalUrl)
					.build();
		
		//Save first to get ID
		UrlShort saved = repo.save(url);
		//generate short code
		String shortcode = Base62util.encode(saved.getId());
		Log.info("Generated Short Code: {}"+shortcode);
		saved.setClickCount(0L);
		saved.setShortCode(shortcode);
		repo.save(saved);
		return "https://localhost:8080/"+shortcode;
		
	}

	@Override
	public String redirect(String shortcode) {
		// TODO Auto-generated method stub
		String cacheUrl  = redisTemplate.opsForValue().get(shortcode);
		if(cacheUrl != null) {
			Log.info("cache hit");
		}
		UrlShort url = repo.findByShortCode(shortcode)
				.orElseThrow(() -> new URLNotFoundException("Short URL Not Found."));
		Log.info("Click Count: {}" , url.getClickCount());
		System.out.println("Click Count  : "+ url.getClickCount());
		url.setClickCount(url.getClickCount()+1);
		url.setLasAccessedAt(LocalDateTime.now());
		repo.save(url);
		return url.getOriginalUrl();
				
	}

	
}
