package com.example.url.URL_SHORTNER.service;

import java.util.Optional;

import com.example.url.URL_SHORTNER.DTO.StatResponseDTO;
import com.example.url.URL_SHORTNER.DTO.UrlRequest;
import com.example.url.URL_SHORTNER.entity.UrlShort;

public interface UrlService {
	String generateShortUrl(UrlRequest request);
	
	//String getOriginalUrl(String shortcode);

	String redirect(String shortcode);
	
	public StatResponseDTO getStatus(String shortcode);
		
	
}
