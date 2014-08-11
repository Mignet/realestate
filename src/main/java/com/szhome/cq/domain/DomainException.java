package com.szhome.cq.domain;

public class DomainException extends RuntimeException {

	private String errorcode;

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public DomainException(String errorcode, String msg) {
		super(msg);
		this.errorcode = errorcode;
	}

	public DomainException(String errorcode, String msg, Throwable cause) {
		super(msg, cause);
		this.errorcode = errorcode;
	}

	public DomainException() {
		super();
	}

	public DomainException(String msg) {
		super(msg);
	}

	public DomainException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DomainException(Throwable cause) {
		super(cause);
	}
}

