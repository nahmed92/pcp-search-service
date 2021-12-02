package com.deltadental.pcp.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PCPBLEnrollee {
	
	private String memberType;
	private String groupNumber;
	private String divisionNumber;	
	private Address memberAddress;
	private String memberLanguage ;		
	private String mtvPersonID ;
	private String pcpEffectiveDate;
	private String pcpEndDate;
	private String pcpIdentifier;
	private String practiceLocation;
	private String recordIdentifier;

}
