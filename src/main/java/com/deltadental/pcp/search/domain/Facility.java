package com.deltadental.pcp.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Facility {
	
	private String facilityID;
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
	private String facilitystatus;
	private String effectiveDate;
	
}
