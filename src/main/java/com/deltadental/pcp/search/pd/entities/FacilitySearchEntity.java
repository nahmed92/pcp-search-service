package com.deltadental.pcp.search.pd.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FacilitySearchEntity {

	/**
	 * This is not a primary key, this is a unique row number for a given search
	 * query
	 */
	@Id
	@Column(name = "row_number", unique = true)
	private Integer rowNum;

	@Column(name = "facility_id")
	private String facilityId;

	@Column(name = "address_line1")
	private String addressLine1;

	@Column(name = "address_line2")
	private String addressLine2;

	@Column(name = "address_line3")
	private String addressLine3;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zip")
	private String zip;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "provider_specialty_desc")
	private String providerSpecialtyDec;

	@Column(name = "contracted")
	private String contracted;

	@Column(name = "group_practice_name")
	private String groupPracticeName;

	@Column(name = "provider_specialty")
	private String providerSpecialty;

	@Column(name = "effective_date")
	private String effectiveDate;

	@Column(name = "facility_status")
	private String facilityStatus;

	@Column(name = "enrollstatus")
	private String enrollStatus;

	@Column(name = "provider_languages")
	private String providerLanguages;
}