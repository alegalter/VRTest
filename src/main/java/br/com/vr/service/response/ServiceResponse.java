package br.com.vr.service.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServiceResponse<T> implements Serializable {

	private static final long serialVersionUID = -6269830165597653977L;
	
	private T response;

}
