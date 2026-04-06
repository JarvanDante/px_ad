package com.example.px.controller;

import com.example.px.dto.AdCreateRequest;
import com.example.px.dto.AdResponse;
import com.example.px.service.AdService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ads")
public class AdController {

	private final AdService adService;

	public AdController(AdService adService) {
		this.adService = adService;
	}

	@GetMapping
	public List<AdResponse> listAds() {
		return adService.listAds();
	}

	@GetMapping("/{id}")
	public AdResponse getAd(@PathVariable Long id) {
		return adService.getAd(id);
	}

	@PostMapping("/add")
	public ResponseEntity<AdResponse> createAd(@RequestBody AdCreateRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(adService.createAd(request));
	}
}
