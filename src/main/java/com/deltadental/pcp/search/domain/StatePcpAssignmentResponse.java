package com.deltadental.pcp.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class StatePcpAssignmentResponse {
	
	private String groupNumber;
	private String divisionNumber;		
	private String state;
	private boolean isStateEligibleforPcp;
	private boolean isGroupDivEligibleforAutoPcp;
	private boolean unlimitedSplitFamilyFlag;
	private boolean unlimitedSplitFamily_INDV_Flag;
	private String returnCode;
	private String returnDescription;
	
}
