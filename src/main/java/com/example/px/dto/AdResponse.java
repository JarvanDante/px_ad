package com.example.px.dto;

import com.example.px.domain.Ad;
import java.time.LocalDateTime;

public record AdResponse(
		Long id,
		String title,
		String content,
		Integer status,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {

	public static AdResponse from(Ad ad) {
		return new AdResponse(
				ad.getId(),
				ad.getTitle(),
				ad.getContent(),
				ad.getStatus(),
				ad.getCreatedAt(),
				ad.getUpdatedAt());
	}
}
