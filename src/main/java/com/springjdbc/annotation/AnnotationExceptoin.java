package com.springjdbc.annotation;

public class AnnotationExceptoin extends RuntimeException {

	public AnnotationExceptoin() {
		super();
	}

	public AnnotationExceptoin(String msg) {
		super(msg);
	}

	public AnnotationExceptoin(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AnnotationExceptoin(Throwable cause) {
		super(cause);
	}
}

