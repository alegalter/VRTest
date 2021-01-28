package br.com.vr.exception;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomExceptionResponse {
	
	@JsonFormat(
		pattern = "yyyy-MM-dd HH:mm:ss", 
		timezone = "GMT-3"
	)
    private Calendar dataHoraErro;
    
    private String descricao;

	public CustomExceptionResponse(String descricao) {
		this.dataHoraErro = Calendar.getInstance();
		this.descricao = descricao;
	}    

}
