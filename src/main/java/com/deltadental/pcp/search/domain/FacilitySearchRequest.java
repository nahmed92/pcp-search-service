package com.deltadental.pcp.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class FacilitySearchRequest {

	private BusinessLevels businesslevel;
	private String benefitpackageID;
	private String groupNumber;
	private String divisionNumber;
	private String enrolleeZipCode;
	private String mtvPersonID;
	private FacilityType facilityType;
	private Facilitystatus facilitystatus;
	private Facilityregion facilityregion;
	private String product;

}
