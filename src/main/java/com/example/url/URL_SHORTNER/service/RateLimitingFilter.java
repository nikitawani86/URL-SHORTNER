package com.example.url.URL_SHORTNER.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RateLimitingFilter extends OncePerRequestFilter {
	
	
	private final RateLimitingService ratelimiterService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String ipAddr = request.getRemoteAddr();
		
		if(!ratelimiterService.isAllowed(ipAddr)) {
			response.setStatus(429);  //429 : Too Many Request
			
			response.getWriter().write("Too Many Request");
			return;
		}
		
	
		filterChain.doFilter(request, response);
	}
	
	

}
