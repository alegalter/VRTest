package br.com.vr;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vr.model.pedido.StatusEnum;
import br.com.vr.vo.pedido.PedidoVO;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class AbstractVRTest {
	
	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired
	protected ObjectMapper mapper;
	
	protected PedidoVO gerarPedido(String cliente, String vencimento, Double valor, StatusEnum status) throws Exception {
		Calendar dataVencimento = Calendar.getInstance();
		Date data = new SimpleDateFormat("dd/MM/yyyy").parse(vencimento);
		dataVencimento.setTime(data);
		
		return PedidoVO.builder()
					.cliente(cliente)
					.dataVencimento(dataVencimento)
					.valor(valor)
					.status(status)
				.build();
	}

}
