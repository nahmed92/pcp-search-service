package com.deltadental.pcp.search.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlServiceRequest {
	
	private List<PCPBLEnrollee> pcpEnrollee;
	private String sourceSystem;
	private String routing;
	
}
