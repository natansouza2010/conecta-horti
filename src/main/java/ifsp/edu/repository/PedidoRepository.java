package ifsp.edu.repository;

import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Pedido;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.usecases.pedido.PedidoDAO;

import java.time.LocalDate;
import java.util.*;

public class PedidoRepository implements PedidoDAO {
    ClienteRepository cliente = new ClienteRepository();
    static Map<Integer, Pedido> pedidosMap = new HashMap<>();

    @Override
    public boolean insert(Pedido obj) {
        pedidosMap.put(obj.getId(), obj);
        return true;
    }

    @Override
    public Pedido findOne(Integer key) {
        if(pedidosMap.containsKey(key)){
            return pedidosMap.get(key);
        }
        return null;
    }

    @Override
    public List<Pedido> listAll() {
        List<Pedido> pedidosList = new ArrayList<>(pedidosMap.values());
        return pedidosList;
    }

    @Override
    public boolean update(Pedido obj) {
        if(pedidosMap.containsKey(obj.getId())){
            pedidosMap.replace(obj.getId(),obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    public List<Pedido> findByDate(LocalDate data) {
        return null;
    }

    @Override
    public List<Pedido> findByNomeCliente(String nome) {
        return null;
    }

    @Override
    public List<Pedido> findByStatus(StatusPedido statusPedido) {
        return null;
    }

    @Override
    public Optional<Pedido> findById(Integer id) {
        if(pedidosMap.containsKey(id)){
            return Optional.of(pedidosMap.get(id));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cliente> findClienteByCpf(String cpf) {
        if(cliente.findByCpf(cpf).isPresent()){
            return cliente.findByCpf(cpf);
        }
        return Optional.empty();
    }
}