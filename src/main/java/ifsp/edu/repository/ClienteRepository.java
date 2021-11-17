package ifsp.edu.repository;

import ifsp.edu.model.Cliente;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.usecases.cliente.ClienteDAO;

import java.util.*;

public class ClienteRepository implements ClienteDAO {
    static Map<String, Cliente> clienteMap = new HashMap<>();

    @Override
    public boolean insert(Cliente obj) {
        clienteMap.put(obj.getCpf(), obj);
        return true;
    }

    @Override
    public Cliente findOne(String key) {
        if(clienteMap.containsKey(key)){
            return clienteMap.get(key);
        }
        return null;
    }

    @Override
    public List<Cliente> listAll() {
        List<Cliente> clientesList = new ArrayList<>(clienteMap.values());
        return clientesList;
    }

    @Override
    public boolean update(Cliente obj) {
        if(clienteMap.containsKey(obj.getCpf())){
            clienteMap.replace(obj.getCpf(),obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String key) {
        if(clienteMap.containsKey(key)){
            clienteMap.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Cliente> findByCpf(String cpf) {
        if(clienteMap.containsKey(cpf)){
            return Optional.of(clienteMap.get(cpf));
        }
        return Optional.empty();
    }
}
