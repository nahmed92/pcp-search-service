package com.deltadental.pcp.search.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvidersRequest {
	
	private String contractId;
	
	private String memberId;
	
	@JsonFormat(shape = Shape.STRING, pattern = "MM-dd-yyyy")
	private LocalDate pcpEffectiveDate;
	
	private String sourceSystem;
	
	private String autoAssignment;

	private String userId;
	
	private Address address;
}
