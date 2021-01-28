package br.com.vr.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	 @Override
	 protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
			 HttpHeaders headers, HttpStatus status, WebRequest request) {

		 List<CustomExceptionResponse> erros = ex.getBindingResult().getFieldErrors()
				 .parallelStream()
				 	.map(erro -> new CustomExceptionResponse(erro.getDefaultMessage()))
				 .collect(Collectors.toList());
		 return new ResponseEntity<>(erros, headers, status);
	 }
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		return new ResponseEntity<Object>(new CustomExceptionResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
	
}
