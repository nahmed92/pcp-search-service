package com.deltadental.pcp.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class BusinessLevels {
	
	private String businessLevel4;
	private String businessLevel5;
	private String businessLevel6;
	private String businessLevel7;
	private String effectiveDate;
	private String endDate;
	private String networkId;
	private String classCode;
	
}
