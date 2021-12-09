package ifsp.edu.usecases.pedido;

import ifsp.edu.dao.DAO;
import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Pedido;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoDAO extends DAO<Pedido, Integer> {

    List<Pedido> findByDate(LocalDate data);
    List<Pedido> findByPeriodoPago(LocalDate data, LocalDate dataFinal);
    List<Pedido> findByCpfCliente(String nome);
    List<Pedido> findByStatus(StatusPedido statusPedido);
    Optional<Pedido> findById(Integer id);
    Optional<Cliente> findClienteByCpf(String cpf);
    boolean atualizarStatusPedido(Pedido pedido);
    boolean escolherMetodoPagamento(Pedido pedido);
    boolean informarEntrega(Pedido pedido);
}
