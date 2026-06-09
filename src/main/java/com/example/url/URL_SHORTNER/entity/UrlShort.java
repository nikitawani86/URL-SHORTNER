package com.example.url.URL_SHORTNER.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
	
	@NotBlank(message = "URL cannot be empty")
	@Pattern(
			regexp = "^(http|https)://.*$",
			message = "Invalid URL Format"
			
)
	private String originalUrl;
	
	@Column(unique = true)

	private String shortCode;
	
	private Long clickCount = 0L;
	
	private LocalDateTime lastAccessedAt;
	
	private LocalDateTime createdAt;
	@PrePersist
	public void prePresist() {
		createdAt  = LocalDateTime.now();
	}
	
	private LocalDateTime expireAt;
	
	
	
}