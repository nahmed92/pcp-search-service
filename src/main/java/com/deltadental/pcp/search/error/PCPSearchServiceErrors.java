package com.deltadental.pcp.search.error;

import org.springframework.http.HttpStatus;

import com.deltadental.platform.common.exception.ServiceException;

import lombok.Getter;

@Getter
public enum PCPSearchServiceErrors {

	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR), 
	NOT_FOUND(HttpStatus.NOT_FOUND),
	BAD_REQUEST(HttpStatus.BAD_REQUEST), 
	FORBIDDEN(HttpStatus.FORBIDDEN), 
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
	
	PROVIDERS_ZIP(HttpStatus.BAD_REQUEST),
	PROVIDERS_AUTOASSIGN(HttpStatus.BAD_REQUEST),
	PROVIDERS_CONTRACTID(HttpStatus.BAD_REQUEST),
	PROVIDERS_MEMBERID(HttpStatus.BAD_REQUEST),
	PROVIDERS_PCPEFFECTIVEDATE(HttpStatus.BAD_REQUEST),
	PROVIDERS_SOURCESYSTEM(HttpStatus.BAD_REQUEST),
	PROVIDERS_USERID(HttpStatus.BAD_REQUEST);

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
