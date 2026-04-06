package com.example.px.dto;

import com.example.px.domain.Ad;
import java.time.LocalDateTime;

// 定义了一个名为 AdResponse 的 record，括号里是它的“组件”（即字段）
public record AdResponse(
		Long id,// 编译器自动生成 private final Long id;
		String title,// 自动生成 public String title() { return title; }
		String content,
		Integer status,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {

	// 这是一个静态工厂方法，用于将 Entity (Ad) 转换成 DTO (AdResponse)
	public static AdResponse from(Ad ad) {
		// 直接调用 record 自动生成的构造器
		return new AdResponse(
				ad.getId(),
				ad.getTitle(),
				ad.getContent(),
				ad.getStatus(),
				ad.getCreatedAt(),
				ad.getUpdatedAt());
	}
}
