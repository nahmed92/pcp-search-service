package com.deltadental.pcp.search.domain;

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
public class FacilitySearchRequest {

	private BusinessLevels businessLevels;
	private String benefitPackageID;
	private String groupNumber;
	private String divisionNumber;
	private String enrolleeZipCode;
	private String mtvPersonID;
	private FacilityType facilityType;
	private Facilitystatus facilityStatus;
	private Facilityregion facilityRegion;
	private String product;

}
