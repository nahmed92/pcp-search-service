package com.deltadental.pcp.search.error;

import org.springframework.http.HttpStatus;

import com.deltadental.platform.common.exception.ServiceException;

import lombok.Getter;

@Getter
public enum PCPSearchServiceErrors {

	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
	
	GETPROVIDERS_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR), 
	PROVIDER_VALIDATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
	PROVIDERS_SEARCH_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),	
	PROVIDERS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR), 
	PCPVALIDATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR), 
	FACILITY_SEARCH_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
	
	PCP_SEARCH_001(HttpStatus.BAD_REQUEST),
	PCP_SEARCH_002(HttpStatus.BAD_REQUEST),
	PROVIDERS_ZIP(HttpStatus.BAD_REQUEST),
	PROVIDERS_ZIP_FORMAT(HttpStatus.BAD_REQUEST),
	PROVIDERS_CONTRACTID(HttpStatus.BAD_REQUEST),
	PROVIDERS_MEMBERID(HttpStatus.BAD_REQUEST),
	PROVIDERS_PCPEFFECTIVEDATE(HttpStatus.BAD_REQUEST),
	PROVIDERS_SOURCESYSTEM(HttpStatus.BAD_REQUEST),
	PROVIDERS_USERID(HttpStatus.BAD_REQUEST), 
	PROVIDERS_CONTRACTID_DIGITS_ONLY(HttpStatus.BAD_REQUEST),
	PROVIDERS_MEMBERID_DIGITS_ONLY(HttpStatus.BAD_REQUEST), 
	ADDRESS_REQUIRED(HttpStatus.BAD_REQUEST),
	PROVIDER_SEARCH_REQUEST(HttpStatus.BAD_REQUEST),
	PCP_VALIDATE_REQUEST(HttpStatus.BAD_REQUEST),
	PROVIDERS_AUTOASSIGN(HttpStatus.BAD_REQUEST),
	FACILITY_NOT_FOUND(HttpStatus.NOT_FOUND),
	FACILITY_ID_REQUIRED(HttpStatus.BAD_REQUEST);

	private final HttpStatus statusCode;

	PCPSearchServiceErrors(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public ServiceException createException(Object... objects) {
		return new ServiceException(this.name(), this.statusCode, objects);
	}

	public HttpStatus httpStatus() {
		return this.statusCode;
	}
}
