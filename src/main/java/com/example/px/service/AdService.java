package com.example.px.service;

import com.example.px.domain.Ad;
import com.example.px.dto.AdCreateRequest;
import com.example.px.dto.AdResponse;
import com.example.px.repository.AdRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdService {

	private final AdRepository adRepository;

	public AdService(AdRepository adRepository) {
		this.adRepository = adRepository;
	}

	@Transactional(readOnly = true)
	public List<AdResponse> listAds() {
		return adRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
				.stream()
				.map(AdResponse::from)
				.toList();
	}

	@Transactional(readOnly = true)
	public AdResponse getAd(Long id) {
		return adRepository.findById(id)
				.map(AdResponse::from)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ad not found"));
	}

	@Transactional
	public AdResponse createAd(AdCreateRequest request) {
		validate(request);

		Ad ad = new Ad();
		ad.setTitle(request.title().trim());
		ad.setContent(request.content().trim());
		ad.setStatus(request.status() == null ? 1 : request.status());

		return AdResponse.from(adRepository.save(ad));
	}

	private void validate(AdCreateRequest request) {
		if (request == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "request body is required");
		}
		if (request.title() == null || request.title().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title is required");
		}
		if (request.content() == null || request.content().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "content is required");
		}
	}
}
