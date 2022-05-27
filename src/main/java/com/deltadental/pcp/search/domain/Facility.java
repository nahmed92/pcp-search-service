package com.deltadental.pcp.search.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Facility {
	
	private String facilityId;
	private String facilityName;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private String specility;
	private String contracted;
	private String enrollStatus;
	private String facilityStatus;
	private String effectiveDate;
	private String providerSpecialtyDec;
	private String groupPracticeName;
	private String providerLanguages;	
}