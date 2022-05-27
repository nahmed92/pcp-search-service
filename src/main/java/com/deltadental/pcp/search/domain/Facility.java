package com.deltadental.pcp.search.domain;

import java.util.List;

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
	private String address;
	private String phoneNumber;
	private String specility;
	private String contracted;
	private String enrollStatus;
	private String facilityStatus;
	private String effectiveDate;
	private String providerSpecialtyDec;
	private List<String> providerLanguages;	
}