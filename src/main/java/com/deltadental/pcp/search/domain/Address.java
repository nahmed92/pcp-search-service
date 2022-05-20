package com.deltadental.pcp.search.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
@Valid
public class Address {

	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	@NotBlank(message = "Zip Code is a mandatory")
	@Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$", message = "ZIP code (U.S. postal code) allow both the five-digit and nine-digit (called ZIP + 4) format.")
	private String zipCode;
	
}
