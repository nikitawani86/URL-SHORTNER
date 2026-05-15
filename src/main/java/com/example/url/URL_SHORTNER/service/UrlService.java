package com.example.url.URL_SHORTNER.service;

public interface UrlService {
	String generateShortUrl(String originalUrl);
	
	String getOriginalUrl(String shortcode);
}
