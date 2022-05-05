package com.deltadental.pcp.search.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	@NotBlank(message = "Zip Code is a mandatory parameter in request")
	@Size(min = 5, max = 9, message = "Zip code accepts only between 5 - 9 characters in length.")
	private String zipCode;
	
}
