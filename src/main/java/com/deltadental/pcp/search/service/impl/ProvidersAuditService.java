package com.deltadental.pcp.search.service.impl;

import java.util.UUID;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deltadental.pcp.search.annotation.ExcludeFromJacocoGeneratedReport;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.domain.ProvidersResponse;
import com.deltadental.pcp.search.entities.ProvidersAuditEntity;
import com.deltadental.pcp.search.repos.ProvidersAuditRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Service("providersAuditService")
@Slf4j
public class ProvidersAuditService {

	@Autowired
	private ProvidersAuditRepo providersAuditRepo;

	@Autowired
	private ObjectMapper mapper;

	public void save(ProvidersRequest providersRequest, ProvidersResponse providersResponse) {
		log.info("START ProvidersAuditService.save()");
		try {
			ProvidersAuditEntity providersAuditEntity = map(providersRequest, providersResponse);
			providersAuditRepo.save(providersAuditEntity);
		} catch (Exception exception) {
			log.error("Unable to save providers audit information into datastore", exception);
		}
		log.info("END ProvidersAuditService.save()");
	}

	@ExcludeFromJacocoGeneratedReport
	private ProvidersAuditEntity map(ProvidersRequest request, ProvidersResponse response) {
		log.info("START ProvidersAuditService.map()");
		ProvidersAuditEntity providersAuditEntity = ProvidersAuditEntity.builder()
				.id(UUID.randomUUID().toString())
				.requestJson(getJsonString(request))
				.responseJson(getJsonString(response))
				.build();
		log.info("END ProvidersAuditService.map()");
		return providersAuditEntity;

	}

	@ExcludeFromJacocoGeneratedReport
	private String getJsonString(Object obj) {
		if (ObjectUtils.notEqual(obj, null)) {
			try {
				return mapper.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				log.error("Exception parsing provider response : ", e);
			}
		}
		return "";
	}
}
