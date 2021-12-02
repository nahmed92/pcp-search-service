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
