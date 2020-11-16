package com.silverxmary.mowers.app.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.silverxmary.mowers.app.exception.ContentErrorException;
import com.silverxmary.mowers.app.exception.FileNameErrorException;
import com.silverxmary.mowers.app.exception.MowersError;
/**
 * 
 * @author soto lisber (silverxmary)
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {

	 /*@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
		    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		        String bodyOfResponse = "This should be application specific";
		        return handleExceptionInternal(ex, bodyOfResponse, 
		          new HttpHeaders(), HttpStatus.CONFLICT, request);
		    }*/
	 
	 

	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(FileNameErrorException.class)
	    public ResponseEntity<Object> handleIncorrectFileNameException(FileNameErrorException e) {
	        MowersError apiError = new MowersError(HttpStatus.BAD_REQUEST, e.getMessage());
	        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getHttpStatus());
	    }

	    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	    @ExceptionHandler(ContentErrorException.class)
	    public ResponseEntity<Object> handleIncorrectContentException(ContentErrorException e) {
	        MowersError apiError = new MowersError(HttpStatus.EXPECTATION_FAILED, e.getMessage());
	        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getHttpStatus());
	    }

	    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	    @ExceptionHandler(NumberFormatException.class)
	    public ResponseEntity<Object> handleNumberFormatException() {
	        MowersError apiError = new MowersError(HttpStatus.NOT_ACCEPTABLE, "Number format exception.");
	        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getHttpStatus());
	    }

}
