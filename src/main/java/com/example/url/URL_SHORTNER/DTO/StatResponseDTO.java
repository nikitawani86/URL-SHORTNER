package com.example.url.URL_SHORTNER.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StatResponseDTO {
	private Long clickCount;
	private LocalDateTime lastAccessedTime;
	private LocalDateTime createdAt;
	private LocalDateTime expireAt;
	private String shortCode;
	private String originalUrl;
	
}
