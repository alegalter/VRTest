package br.com.vr.service.pedido;

import java.util.List;

import br.com.vr.dto.pedido.VencimentoPedidoDto;
import br.com.vr.model.pedido.Pedido;
import br.com.vr.model.pedido.StatusEnum;
import br.com.vr.vo.pedido.PedidoVO;

public interface PedidoService {

	PedidoVO consultar(String idPedido);

	List<Pedido> listar();
	
	Pedido criar(PedidoVO pedidoVO);

	void alterar(String idPedido, PedidoVO pedidoVO);

	void excluir(String idPedido, PedidoVO pedidoVO);

	void atualizarStatus(String idPedido, StatusEnum status);

	void atualizarVencimento(String idPedido, VencimentoPedidoDto vencimentoPedidoDto);

	List<PedidoVO> converterListaPedidoVO(List<Pedido> pedidos);
	
}
