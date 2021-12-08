package com.deltadental.pcp.search.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrolleeBLRespose {

	private String memberType;
	private List<BusinessLevels> businessLevels;
	private List<BenefitPackage> benefitPackages;
	private String providerId;
	private String groupNumber;
	private String divisionNumber;
	private int businessLevelCount;
	private String statusCode;
	private String errorMessage;

}
