package br.edu.ufcg.dsc.opi;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.edu.ufcg.dsc.opi.util.ExceptionResponse;

/**
 * Captures system exceptions in the context of the Dispatcher Servlet. That is,
 * exceptions from {@link FilterRegistrationBean} are not dealt with here.
 * 
 * @see <a href=
 *      "https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc">Exception
 *      Handling in Spring</a>
 * 
 * @author Eri Jonhson
 */
@ControllerAdvice
@RestController
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

	public GlobalDefaultExceptionHandler() {
	};

	/**
	 * Captures generic exceptions and sends error occurred with status Internal
	 * Server Error (HTTP 500).
	 * 
	 * @param exception
	 * @param request
	 * @return ExceptionResponse
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public final ExceptionResponse handleAllExceptions(Exception exception, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
				request.getDescription(false));
		return exceptionResponse;
	}

	/**
	 * Handle all validate problems.
	 * 
	 * @see {@link ResponseEntityExceptionHandler}
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.CONFLICT);
	}

	/**
	 * Captures Constraint Violations in validations and sends error occurred with
	 * status Bad Request (HTTP 400).
	 * 
	 * @param exception
	 * @param request
	 * @return ExceptionResponse
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public final ExceptionResponse handleConstraintViolationException(ConstraintViolationException exception,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
				request.getDescription(false));
		return exceptionResponse;
	}

	/**
	 * Captures Constraint Violations in validations and sends error occurred with
	 * status Bad Request (HTTP 400).
	 * 
	 * @param exception
	 * @param request
	 * @return ExceptionResponse
	 */
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(AccessDeniedException.class)
	public final ExceptionResponse handleAccessDeniedException(AccessDeniedException exception, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
				request.getDescription(false));
		return exceptionResponse;
	}

}
