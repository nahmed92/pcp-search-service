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
public class PCPRequest {
	
	private String contractID;
	private boolean autoAssignmentFlag;	
	private List<PCPEnrollee> enrollees;
	private PrimaryEnrolleePCPInfo primaryEnrolleePCPInfo;
	private PCPRefineSearch pcpRefineSearch;

}
