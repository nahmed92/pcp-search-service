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
public class EnrolleeBLRespose {

	private String memberType;
	private List<BusinessLevels> businessLevels;
	private List<BenefitPackage> benPkgList;
	private String providerId;
	private String groupNumber;
	private String divisionNumber;
	private int BusinessLevelCount;
	private String statusCode;
	private String errorMessage;

}
