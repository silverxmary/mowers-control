package com.silverxmary.mowers.app.exception;

import org.springframework.http.HttpStatus;
/**
 * 
 * @author soto lisber (silverxmary)
 *
 */
public class MowersError {

    private HttpStatus httpStatus;
    private String message;

    public MowersError(HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
    }

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
    
    
}
