package br.com.vr.repository.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vr.model.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, String> {

}
