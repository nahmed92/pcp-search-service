package com.deltadental.pcp.search.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SourceSystemValidator implements ConstraintValidator<SourceSystem, String>  {
	
	@Override
	public boolean isValid(String sourceSystem, ConstraintValidatorContext context) {
		if(StringUtils.isEmpty(sourceSystem)) {
			context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Source system cannot be empty").addConstraintViolation();
            return false;
		}
		return true;
	}

}
