package com.deltadental.pcp.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessLevelsRequest {
	
	private String businessLevel4;
	private String businessLevel5;
	private String businessLevel6;
	private String businessLevel7;
	private String effectiveDate;
	private String endDate;
	private String networkId;
	
}
