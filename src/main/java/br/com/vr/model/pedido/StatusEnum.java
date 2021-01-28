package br.com.vr.model.pedido;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.vr.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum StatusEnum {
	
	ABERTO("ABERTO", "Aberto"),
	FINALIZADO("FINALIZADO", "Finalizado"),
	CANCELADO("CANCELADO", "Cancelado");
	
	private final String id;
	
	private final String descricao;
	
	public static StatusEnum buscarEnum(String id) {
		 Optional<StatusEnum> status = Arrays.asList(values())
	                .parallelStream().filter(s -> s.getId().equalsIgnoreCase(id))
	                .findFirst();
		 if (!status.isPresent()) {
			 throw new ResourceNotFoundException("Status de pedido " + id + " não encontrado.");
		 }
		 return status.get();
	 }

}
