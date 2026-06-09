package com.example.url.URL_SHORTNER.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
	
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlRequest {
	@NotBlank
	public String originalUrl;
	
	private String alias;
	
	private LocalDateTime expireAt;
}
