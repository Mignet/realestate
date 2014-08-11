package com.springjdbc.annotation;

import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateConverter implements Converter {
	@Override
	public Object convert(Class type, Object value) {
		if (value == null || value.toString().length() < 1) {
			return null;
		}
		if (value instanceof Date) {
			return new Date(((Date) value).getTime());
		}
		try {
			return (Date) value;
		} catch (Exception ex) {
			throw new AnnotationExceptoin(
					"Exception:BeanUtils.DateConverter.convert():"
							+ ex.getMessage());
		}
	}
}

