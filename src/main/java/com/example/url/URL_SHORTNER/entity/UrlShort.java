package com.example.url.URL_SHORTNER.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "URL-SHORTNER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlShort {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String originalUrl;
	
	@Column(unique = true)
	private String shortCode;
	
	private LocalDate createdAt;

	
}