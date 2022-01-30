package com.devsuperior.movieflix.exceptions;

public class UnprocessableException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnprocessableException(String msg) {
		super(msg);
	}
}
