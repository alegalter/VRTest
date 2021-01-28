package br.com.vr;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import br.com.vr.model.pedido.StatusEnum;
import br.com.vr.vo.pedido.PedidoVO;

public class PedidoResourceSucessoTest extends AbstractVRTest {

	@Test
	public void testConsultar() throws Exception {
		mockMvc.perform(get("/pedidos/1")).andExpect(status().isOk());
	}
	
	@Test
	public void testListar() throws Exception {
		mockMvc.perform(get("/pedidos")).andExpect(status().isOk());
	}
	
	@Test
	public void testCriar() throws Exception {
		PedidoVO pedido = gerarPedido("Teste Insercao", "30/12/2020", 15d, StatusEnum.ABERTO);
		
		mockMvc.perform(
			post("/pedidos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(pedido))
		).andExpect(status().isCreated());
	}
	
	@Test
	public void testAlterar() throws Exception {
		PedidoVO pedido = gerarPedido("Teste Atualizacao", "31/12/2020", 150.30d, StatusEnum.FINALIZADO);
		
		mockMvc.perform(
				put("/pedidos/1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(pedido))
		).andExpect(status().isOk());
	}
	
	@Test
	public void testExcluir() throws Exception {
		PedidoVO pedido = gerarPedido("Teste Exclusao", "31/12/2020", 150.30d, StatusEnum.FINALIZADO);
		
		mockMvc.perform(
				delete("/pedidos/2")
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(pedido))
		)
		.andDo(print())
		.andExpect(status().isOk());
	}

	@Test
	public void testAtualizarStatusAberto() throws Exception {
		mockMvc.perform(patch("/pedidos/1/status/Aberto")).andExpect(status().isNoContent());
	}
	
	@Test
	public void testAtualizarStatusCancelado() throws Exception {
		mockMvc.perform(patch("/pedidos/1/status/cancelado")).andExpect(status().isNoContent());
	}
	
	@Test
	public void testAtualizarStatusFinalizado() throws Exception {
		mockMvc.perform(patch("/pedidos/1/status/finalizado")).andExpect(status().isNoContent());
	}
	
	@Test
	public void testAtualizarVencimentoPedido() throws Exception {
		mockMvc.perform(
				patch("/pedidos/1/vencimento")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{ \"dataVencimento\": \"2050-01-15\" }")
		).andExpect(status().isNoContent());
	}
	
}
