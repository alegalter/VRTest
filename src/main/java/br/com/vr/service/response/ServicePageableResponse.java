package br.com.vr.service.response;

import lombok.Getter;

@Getter
public class ServicePageableResponse<T> {

	private T response;
	
	public ServicePageableResponse(T response) {
		this.response = response;
	}

}
