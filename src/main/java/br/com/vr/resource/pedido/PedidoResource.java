package br.com.vr.resource.pedido;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vr.dto.pedido.PedidoListarDto;
import br.com.vr.dto.pedido.VencimentoPedidoDto;
import br.com.vr.model.pedido.Pedido;
import br.com.vr.model.pedido.StatusEnum;
import br.com.vr.service.pedido.PedidoService;
import br.com.vr.service.response.ServicePageableResponse;
import br.com.vr.service.response.ServiceResponse;
import br.com.vr.vo.pedido.PedidoVO;
import lombok.Getter;

@Getter
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	private final PedidoService pedidoService;
	
	@Autowired
	public PedidoResource(final PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@GetMapping(value = "/{idPedido}")
	public ResponseEntity<ServiceResponse<PedidoVO>> consultar(@PathVariable("idPedido") String idPedido) {
		PedidoVO pedido = getPedidoService().consultar(idPedido);
		return ResponseEntity.ok(new ServiceResponse<PedidoVO>(pedido));
	}
	
	@GetMapping
	public ResponseEntity<ServicePageableResponse<List<PedidoVO>>> listar(PedidoListarDto pedidoListarDto) {
		List<Pedido> pedidos = getPedidoService().listar();
		List<PedidoVO> pedidosVO = getPedidoService().converterListaPedidoVO(pedidos);
		
		return ResponseEntity.ok(new ServicePageableResponse<List<PedidoVO>>(pedidosVO));
	}
	
	@PostMapping
	public ResponseEntity<ServiceResponse<Void>> criar(@Valid @RequestBody PedidoVO pedido) {
		pedido = getPedidoService().criar(pedido).gerarVO();
		
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idPedido}")
	                .build().toUri();
		 return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{idPedido}")
	public ResponseEntity<ServiceResponse<Void>> alterar(
			@PathVariable("idPedido") String idPedido,
            @Valid @RequestBody PedidoVO pedido) {
		getPedidoService().alterar(idPedido, pedido);
		
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/{idPedido}")
	public ResponseEntity<ServiceResponse<Void>> excluir(
            @PathVariable("idPedido") String idPedido, 
            @Valid @RequestBody PedidoVO pedido) {
		getPedidoService().excluir(idPedido, pedido);
		
		return ResponseEntity.ok().build();
	}

	@PatchMapping(value = "/{idPedido}/status/{status}")
	public ResponseEntity<ServiceResponse<Void>> atualizarStatus(
            @PathVariable("idPedido") String idPedido,
            @PathVariable("status") StatusEnum status) {
		getPedidoService().atualizarStatus(idPedido, status);
		
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{idPedido}/vencimento")
	public ResponseEntity<ServiceResponse<Void>> atualizarVencimentoPedido(
            @PathVariable("idPedido") String idPedido,
            @Valid @RequestBody VencimentoPedidoDto vencimentoPedidoDto) {
		getPedidoService().atualizarVencimento(idPedido, vencimentoPedidoDto);
		return ResponseEntity.noContent().build();
	}

}
