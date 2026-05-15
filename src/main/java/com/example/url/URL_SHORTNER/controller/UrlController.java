package com.example.url.URL_SHORTNER.controller;

import org.springframework.stereotype.*;
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

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UrlController {
	private final UrlServiceImpl service;
	
	@PostMapping("/shortUrl")
	public UrlrResponse shortednUrl(@RequestBody @Valid UrlRequest request) {
		String shortUrl = service.generateShortUrl(request.getOriginalUrl());
		
		return new UrlrResponse(shortUrl);
	}
}
 	