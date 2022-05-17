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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "providers_audit", schema = "dbo")
@Entity
public class ProvidersAuditEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "request_json")
	private String requestJson;

	@Column(name = "response_json")
	private String responseJson;

	@Column(name = "created_at")
	@CreationTimestamp
	private Timestamp createdAt;

	@Column(name = "last_updated_at")
	@UpdateTimestamp
	private Timestamp lastUpdatedAt;

}
