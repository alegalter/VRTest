package br.com.vr.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.vr.model.pedido.StatusEnum;

@Component
public class StatusEnumConverter implements Converter<String, StatusEnum> {

	@Override
	public StatusEnum convert(String value) {
		return StatusEnum.buscarEnum(value);
	}

}
