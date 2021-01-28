package br.com.vr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3107356823041398396L;

	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}
	
}
