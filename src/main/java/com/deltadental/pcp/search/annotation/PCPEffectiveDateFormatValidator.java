package com.deltadental.pcp.search.annotation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PCPEffectiveDateFormatValidator implements ConstraintValidator<PCPEffectiveDateFormat, String>  {
	
	private static final DateFormat _DATEFORMAT = new SimpleDateFormat("MM-dd-yyyy");
	
	@Override
    public void initialize(PCPEffectiveDateFormat constraint) {
    
	}

	@Override
	public boolean isValid(String pcpEffectiveDate, ConstraintValidatorContext context) {
		if(StringUtils.isEmpty(pcpEffectiveDate)) {
			context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Source system cannot be empty!").addConstraintViolation();
            return false;
		} else {
			try {
				_DATEFORMAT.parse(pcpEffectiveDate);
				return true;
			} catch (Exception e) {
				context.disableDefaultConstraintViolation();
	            context.buildConstraintViolationWithTemplate("PCP Effective Date format should be 'MM-dd-yyyy'!").addConstraintViolation();
	            return false;
			}
		}
	}
}
