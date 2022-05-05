package com.deltadental.pcp.search.domain;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Valid
public class PCPValidateRequest {

	@NotBlank
	private String contractId;
	
	@NotBlank
    private String lookAheadDays;
    
    @NotBlank
    private String memberType;
    
    @NotBlank
    private String mtvPersonId;
    
    @NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "MM-dd-yyyy")
    private LocalDate pcpEffDate;    
    
    @NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "MM-dd-yyyy")
    private LocalDate pcpEndDate;
    
    @NotBlank
    private String product;
    
    @NotBlank
    private String providerId;
    
    private String recordIdentifier;
    
    @NotBlank
    private String sourceSystem;
}
