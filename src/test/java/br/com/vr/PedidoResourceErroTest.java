package br.com.vr;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import br.com.vr.exception.ResourceNotFoundException;

public class PedidoResourceErroTest extends AbstractVRTest {

	@Test
	public void testConsultar() throws Exception {
		MvcResult retorno = mockMvc.perform(get("/pedidos/invalido"))
			.andExpect(status().isNotFound())
			.andReturn();
		assertTrue(ResourceNotFoundException.class.isAssignableFrom(retorno.getResolvedException().getClass()));
	}
	
	@Test
	public void testCriarSemCliente() throws Exception {
		
		mockMvc.perform(
			post("/pedidos")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"valor\":15.0,\"status\":\"ABERTO\",\"dataVencimento\":\"2020-12-30\"}")
		).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testCriarSemValor() throws Exception {
		
		mockMvc.perform(
			post("/pedidos")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"cliente\":\"Teste Insercao\",\"status\":\"ABERTO\",\"dataVencimento\":\"2020-12-30\"}")
		).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testCriarSemDataVencimento() throws Exception {
		
		mockMvc.perform(
			post("/pedidos")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"cliente\":\"Teste Insercao\",\"status\":\"ABERTO\"}")
		).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testCriarDataVencimentoPassada() throws Exception {
		
		mockMvc.perform(
			post("/pedidos")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":null,\"cliente\":\"Teste Insercao\",\"valor\":15.0,\"status\":\"ABERTO\",\"dataVencimento\":\"2020-12-18\"}")
		).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testAlterarSemValor() throws Exception {
		
		mockMvc.perform(
				put("/pedidos/1")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"cliente\": \"teste\"}")
		).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testAlterarSemCliente() throws Exception {
		
		mockMvc.perform(
				put("/pedidos/1")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"valor\": 1.33}")
		).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testAlterarInexistente() throws Exception {
		
		MvcResult retorno = mockMvc.perform(
				put("/pedidos/1000")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"cliente\": \"teste\", \"valor\": 1.33, \"dataVencimento\": \"2050-12-31\"}")
		)
			.andExpect(status().isNotFound())
			.andReturn();
		assertTrue(ResourceNotFoundException.class.isAssignableFrom(retorno.getResolvedException().getClass()));
	}
	
	@Test
	public void testExcluir() throws Exception {
		
		MvcResult retorno = mockMvc.perform(
				delete("/pedidos/2000")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"cliente\": \"teste\", \"valor\": 1.33, \"dataVencimento\": \"2050-12-31\"}")
		)
		.andExpect(status().isNotFound())
		.andReturn();
		assertTrue(ResourceNotFoundException.class.isAssignableFrom(retorno.getResolvedException().getClass()));
	}
	
	@Test
	public void testAtualizarStatusAberto() throws Exception {
		mockMvc.perform(patch("/pedidos/1111/status/Aberto")).andExpect(status().isNotFound());
	}
	
	@Test
	public void testAtualizarStatusCancelado() throws Exception {
		mockMvc.perform(patch("/pedidos/1111/status/cancelado")).andExpect(status().isNotFound());
	}
	
	@Test
	public void testAtualizarStatusFinalizado() throws Exception {
		mockMvc.perform(patch("/pedidos/1111/status/finalizado")).andExpect(status().isNotFound());
	}
	
	@Test
	public void testAtualizarVencimentoPedidoInvalido() throws Exception {
		mockMvc.perform(
				patch("/pedidos/1/vencimento")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{ \"dataVencimento\": \"15/01/2020\" }")
		).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testAtualizarVencimentoPedidoPassado() throws Exception {
		mockMvc.perform(
				patch("/pedidos/1/vencimento")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{ \"dataVencimento\": \"2020-01-01\" }")
		).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testAtualizarVencimentoPedidoInexistente() throws Exception {
		mockMvc.perform(
				patch("/pedidos/1000/vencimento")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{ \"dataVencimento\": \"2050-01-01\" }")
		).andExpect(status().isNotFound());
	}
	
}
