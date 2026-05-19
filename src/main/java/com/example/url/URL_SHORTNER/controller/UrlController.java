package com.example.url.URL_SHORTNER.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.url.URL_SHORTNER.DTO.UrlRequest;
import com.example.url.URL_SHORTNER.DTO.UrlrResponse;
import com.example.url.URL_SHORTNER.service.UrlService;
import com.example.url.URL_SHORTNER.service.UrlServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UrlController {
	private final UrlServiceImpl service;
	
	private static final Logger log = LoggerFactory.getLogger(UrlController.class);
	

	
	@PostMapping("/shortUrl")
	public UrlrResponse shortednUrl(@RequestBody @Valid UrlRequest request) {
		String shortUrl = service.generateShortUrl(request.getOriginalUrl());
		
		return new UrlrResponse(shortUrl);
	}
	
	@GetMapping("/{shortcode}")
	public ResponseEntity<Void> redirect(@PathVariable String shortcode){
		String originalUrl = service.redirect(shortcode);
		log.info("Original URL: {}", originalUrl);
		System.out.println(originalUrl.length());
		return ResponseEntity.status(HttpStatus.FOUND)
					.location(URI.create(originalUrl))
					.build();
	}
}
 	