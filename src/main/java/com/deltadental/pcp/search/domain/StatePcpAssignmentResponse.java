package com.deltadental.pcp.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
