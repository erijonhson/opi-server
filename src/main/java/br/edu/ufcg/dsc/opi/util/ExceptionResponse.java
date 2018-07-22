package br.edu.ufcg.dsc.opi.util;

import java.time.Instant;

public class ExceptionResponse {

	private Instant timestamp;
	private String message;
	private String details;

	public ExceptionResponse(Instant timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
