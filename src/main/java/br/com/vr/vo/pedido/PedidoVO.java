package br.com.vr.vo.pedido;

import java.io.Serializable;
import java.util.Calendar;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.OptBoolean;

import br.com.vr.model.pedido.Pedido;
import br.com.vr.model.pedido.StatusEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PedidoVO implements Serializable {

	private static final long serialVersionUID = 2185613948857377821L;
	
    @JsonProperty(access = Access.READ_ONLY)
	private String id;
	
	@NotEmpty(message = "Informe o cliente")
	private String cliente;
	
	@NotNull(message = "Informe o valor do pedido")
	@Min(value = 1, message = "Valor mínimo do pedido é 1,00")
	@Max(value = 10000, message = "Valor máximo do pedido é 10.000,00")
	private Double valor;
	
	private StatusEnum status;
	
	@NotNull(message = "Informe a data de vencimento")
	@JsonFormat(
		shape = JsonFormat.Shape.STRING,
		pattern = "yyyy-MM-dd",
		timezone = "GMT-3",
		lenient = OptBoolean.FALSE
	)
	@FutureOrPresent(message = "Data de Vencimento deve ser presente ou futuro, ex: 2020-12-23")
	private Calendar dataVencimento;
	
	public Pedido gerarPedido() {
		return Pedido.builder()
					.id(id)
					.cliente(cliente)
					.valor(valor)
					.dataVencimento(dataVencimento)
				.build();
	}

}
