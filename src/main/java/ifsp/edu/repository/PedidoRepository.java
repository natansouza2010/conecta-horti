package ifsp.edu.repository;

import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Pedido;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.usecases.pedido.PedidoDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
        if(pedidosMap.containsKey(key)){
            pedidosMap.remove(key);
            return true;
        }


        return false;
    }

    @Override
    public List<Pedido> findByDate(LocalDate data) {
        List<Pedido> array = new ArrayList<>(pedidosMap.values());
        return array.stream().filter(p-> p.getDataPedido().equals(data)).collect(Collectors.toList());
    }

    @Override
    public List<Pedido> findByPeriodo(LocalDate data, LocalDate dataFinal) {
        List<Pedido> array = new ArrayList<>(pedidosMap.values());
        return array.stream().filter(p-> p.getDataPedido().isEqual(data) || p.getDataPedido().isAfter(data)
                && p.getDataPedido().isBefore(dataFinal) || p.getDataPedido().isEqual(dataFinal)).collect(Collectors.toList());
    }

    @Override
    public List<Pedido> findByCpfCliente(String nome) {
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

    @Override
    public boolean atualizarStatusPedido(Pedido pedido) {
        if (pedido.getStatus() == StatusPedido.A_PAGAR) {
            pedido.setStatus(StatusPedido.PAGO);
            return true;

        }

        return false;
    }

    @Override
    public boolean escolherMetodoPagamento(Pedido pedido) {
        return false;
    }

    @Override
    public boolean informarEntrega(Pedido pedido) {
        return false;
    }
}
