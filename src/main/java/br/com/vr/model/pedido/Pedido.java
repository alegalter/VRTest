package br.com.vr.model.pedido;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.vr.vo.pedido.PedidoVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {

	private static final long serialVersionUID = -1269215794135765823L;
	
	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	private String id;
	
	@Column(nullable = false)
	private String cliente;
	
	@Column(nullable = false)
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusEnum status;
	
	@Column(nullable = false)
	private Calendar dataVencimento;
	
	public PedidoVO gerarVO() {
		return PedidoVO.builder()
					.id(id)
					.cliente(cliente)
					.valor(valor)
					.status(status)
					.dataVencimento(dataVencimento)
				.build();
	}

}
