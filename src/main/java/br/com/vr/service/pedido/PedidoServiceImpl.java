package br.com.vr.service.pedido;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.dto.pedido.VencimentoPedidoDto;
import br.com.vr.exception.ResourceNotFoundException;
import br.com.vr.model.pedido.Pedido;
import br.com.vr.model.pedido.StatusEnum;
import br.com.vr.repository.pedido.PedidoRepository;
import br.com.vr.vo.pedido.PedidoVO;
import lombok.Getter;

@Getter
@Service
public class PedidoServiceImpl implements PedidoService {

	private final PedidoRepository repository;

	@Autowired
	public PedidoServiceImpl(final PedidoRepository repository) {
		this.repository = repository;
	}

	@Override
	public PedidoVO consultar(String idPedido) {
		return consultarPedidoPorId(idPedido).gerarVO();
	}
	
	@Override
	public List<Pedido> listar() {
		return getRepository().findAll();
	}

	@Override
	public Pedido criar(PedidoVO pedidoVO) {
		Pedido pedido = pedidoVO.gerarPedido();
		pedido.setStatus(StatusEnum.ABERTO);
		
		return getRepository().save(pedido);
	}

	@Override
	public void alterar(String idPedido, PedidoVO pedidoVO) {
		Pedido pedido = consultarPedidoPorId(idPedido);
		pedido.setCliente(pedidoVO.getCliente());
		pedido.setValor(pedidoVO.getValor());
		pedido = getRepository().save(pedido);
	}
	
	@Override
	public void excluir(String idPedido, PedidoVO pedidoVO) {
		Pedido pedido = consultarPedidoPorId(idPedido);
		getRepository().delete(pedido);
	}
	
	@Override
	public void atualizarStatus(String idPedido, StatusEnum status) {
		Pedido pedido = consultarPedidoPorId(idPedido);
		pedido.setStatus(status);
		getRepository().save(pedido);
	}

	@Override
	public void atualizarVencimento(String idPedido, VencimentoPedidoDto vencimento) {
		Pedido pedido = consultarPedidoPorId(idPedido);
		pedido.setDataVencimento(vencimento.getDataVencimento());
		
		getRepository().save(pedido);
	}
	
	@Override
	public List<PedidoVO> converterListaPedidoVO(List<Pedido> pedidos) {
		return pedidos.parallelStream().map(p -> p.gerarVO()).collect(Collectors.toList());
	}
	
	private Pedido consultarPedidoPorId(String idPedido) {
		return getRepository().findById(idPedido)
				.orElseThrow(() -> new ResourceNotFoundException("Pedido " + idPedido + " não encontrado."));
	}

}
