package com.deltadental.pcp.search.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class PCPRequest {
	
	private String contractID;
	private boolean autoAssignmentFlag;	
	private List<PCPEnrollee> enrollees;
	private PrimaryEnrolleePCPInfo primaryEnrolleePCPInfo;
	private PCPRefineSearch pcpRefineSearch;

}
