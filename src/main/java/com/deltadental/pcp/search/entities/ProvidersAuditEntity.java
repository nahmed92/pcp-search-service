package com.deltadental.pcp.search.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SuppressWarnings("deprecation")
@Data
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PROVIDERS_AUDIT", schema = "dbo")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class ProvidersAuditEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name = "PROVIDERS_AUDIT_ID")
	private String providersAuditId;

	@Column(name = "AUTO_ASSIGNMENT")
	private String autoAssignment;

	@Column(name = "CONTRACT_ID")
	private String contractId;

	@Column(name = "MEMBER_ID")
	private String memberId;

	@Column(name = "PCP_EFFECTIVE_DATE")
	private String pcpEffectiveDate;

	@Column(name = "SOURCE_SYSTEM")
	private String sourceSystem;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "ADDRESS_LINE1")
	private String addressLineOne;

	@Column(name = "ADDRESS_LINE2")
	private String addressLineTwo;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "ZIP_CODE")
	private String zipCode;

	@Column(name = "CREATION_TS")
	private Timestamp crationTs;

	@Column(name = "LAST_MAINT_TS")
	private Timestamp lastMaintTs;

    @Column(name = "RESPONSE_JSON")
	private String responseJson;

	@PrePersist
	public void onInsert() {
		crationTs = Timestamp.from(ZonedDateTime.now(ZoneId.of("America/Los_Angeles")).toInstant());
		lastMaintTs = crationTs;
	}

	@PreUpdate
	public void onUpdate() {
		lastMaintTs = Timestamp.from(ZonedDateTime.now(ZoneId.of("America/Los_Angeles")).toInstant());
	}
}
