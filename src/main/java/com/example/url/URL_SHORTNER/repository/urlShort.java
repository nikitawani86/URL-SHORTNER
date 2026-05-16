package com.example.url.URL_SHORTNER.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.url.URL_SHORTNER.entity.UrlShort;

public interface urlShort  extends JpaRepository<UrlShort,Long>{
	
	Optional<UrlShort> findByShortCode(String shortCode);
	
	

}
