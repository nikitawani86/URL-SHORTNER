package com.example.url.URL_SHORTNER.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.url.URL_SHORTNER.entity.UrlShort;
import com.example.url.URL_SHORTNER.repository.urlShort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl  implements UrlService{
	private final urlShort repo;
	@Override
	public String generateShortUrl(String originalUrl) {
		// TODO Auto-generated method stub
		UrlShort url = UrlShort.builder()
					.originalUrl(originalUrl)
					.createdAt(LocalDate.now())
					.build();
		
		//Save first to get ID
		UrlShort saved = repo.save(url);
		//generate short code
		String shortcode = Base62util.encode(saved.getId());
		saved.setShortCode(shortcode);
		repo.save(saved);
		return "https://localhost:8080/"+shortcode;
		
	}

	@Override
	public String getOriginalUrl(String shortcode) {
		// TODO Auto-generated method stub
		return repo.findByShortCode(shortcode)
				.orElseThrow(() -> new RuntimeException("URL Not Found"))
				.getOriginalUrl();
	}

}
