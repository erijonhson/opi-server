package com.ufcg.opi;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
public class GlobalDefaultExceptionHandler {

	public GlobalDefaultExceptionHandler() {
	};

	/**
	 * Captures generic exceptions and sends error occurred with status Bad Request
	 * (HTTP 400).
	 * 
	 * @param HttpServletRequest
	 * @param Throwable
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { Throwable.class, Exception.class })
	@ResponseBody
	public RuntimeException defaultErrorHandler(HttpServletRequest req, Throwable t) {
		if (StringUtils.isEmpty(t.getMessage()))
			return new RuntimeException("Erro desconhecido!");
		return new RuntimeException(t.getMessage());
	}

}
