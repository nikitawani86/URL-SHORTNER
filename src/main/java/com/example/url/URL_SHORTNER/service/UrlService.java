package com.example.url.URL_SHORTNER.service;

import com.example.url.URL_SHORTNER.DTO.UrlRequest;

public interface UrlService {
	String generateShortUrl(UrlRequest request);
	
	//String getOriginalUrl(String shortcode);

	String redirect(String shortcode);
}
