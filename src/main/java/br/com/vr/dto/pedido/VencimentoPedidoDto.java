package br.com.vr.dto.pedido;

import java.io.Serializable;
import java.util.Calendar;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;

import lombok.Getter;

@Getter
public class VencimentoPedidoDto implements Serializable {

	private static final long serialVersionUID = 4621622117092798855L;

	@NotNull(message = "Informe a data de vencimento")
	@JsonFormat(
		shape = JsonFormat.Shape.STRING,
		pattern = "yyyy-MM-dd",
		timezone = "GMT-3",
		lenient = OptBoolean.FALSE
	)
	@FutureOrPresent(message = "Data de Vencimento deve ser presente ou futuro, ex: 2020-12-23")
	private Calendar dataVencimento;
	
}
