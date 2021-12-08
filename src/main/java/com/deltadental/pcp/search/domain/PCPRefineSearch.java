package com.deltadental.pcp.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class PCPRefineSearch {

	private String city;
	private String state;
	private String zipCode;
	private String distance;
	private String location;
	private String keyword;
	private String language;
	private String officeHours;
	private String officeAccess;
	private String patientConsideration;
	private String others;
	private String sortND;
	private String gender;
	private String workMode;

}
