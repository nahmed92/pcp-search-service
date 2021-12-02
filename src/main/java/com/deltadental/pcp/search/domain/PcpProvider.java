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
public class PcpProvider {
	
    private String providerFacilityId;
	private String providerFacilityName; 
	private String practiceLocationNumber;
	private String groupPracticeNumber;		
	private String providerType;	
	private String mtvNetworkId;		
	private String practiceLocationPhone;
	private String providerAccessibleFacility;
	private String providerWorkMode;
	private String maxMemberCount;
	private String patientCount;
	private String facilityLatitude;
	private String facilityLongitude;
	private Address providerAddress;
	private OfficeHour officeHours;
	private List<String> languages;
	private String distance;
	private String minAge;
	private String maxAge;
	private String specialtyCode;
	private String specialtyDescription;
	private List<BusinessLevels> businesslevels;
	
}
