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
public class EnrolleeDetail {

	private String memberType;
	private String groupNumber;
	private String divisionNumber;
	private List<BusinessLevels> businesslevels;
	private List<BenefitPackage> benefitpackages;
	private List<PcpProvider> pcpproviders;
	private String enrolleeStatusCode; 
	private boolean pcpValidationFlag; //This is for EEP and IP to determine if the input provider is valid
	private List<String> errorMessages ;
	private String recordIdentifier;
	private String numberOfFacilities;

}
