package com.deltadental.pcp.search.domain;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
@Valid
public class ProvidersRequest {
	
	@NotBlank
	private String contractId;
	
	@NotBlank
	@Size(min =2, max = 2, message = "Member ID should be two chars length!")
	private String memberId;
	
	@NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "MM-dd-yyyy")
	private LocalDate pcpEffectiveDate;
	
	@NotBlank
	private String sourceSystem;
	
	@NotBlank
	@Pattern(regexp = "Y|N", flags = Pattern.Flag.CASE_INSENSITIVE)
	@Size(min =1, max = 1, message = "Auto Assignment Value should be Y or N only!")
	private String autoAssignment;
	
	@NotBlank
	private String userId;
	
	@Valid
	private Address address;
}
