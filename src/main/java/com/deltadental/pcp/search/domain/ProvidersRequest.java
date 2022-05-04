package com.deltadental.pcp.search.domain;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvidersRequest {
	
	@NotNull
	private String contractID;
	
	@NotNull
	@Size(min =2, max = 2, message = "Member ID should be two chars length!")
	private String memberId;
	
	@NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "MM-dd-yyyy")
	private LocalDate pcpEffectiveDate;
	
	@NotNull
	private String sourceSystem;
	
	@NotNull
	@Size(min =1, max = 1, message = "Auto Assignment Value should be Y or N only!")
	private String autoAssignment;
	
	@NotNull
	private String userID;
	
	@Valid
	private Address address;
}
