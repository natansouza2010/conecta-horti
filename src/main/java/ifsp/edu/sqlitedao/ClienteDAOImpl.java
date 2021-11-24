package ifsp.edu.sqlitedao;

import ifsp.edu.model.Cliente;
import ifsp.edu.usecases.cliente.ClienteDAO;

import java.util.List;
import java.util.Optional;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public boolean insert(Cliente obj) {
        return false;
    }

    @Override
    public Cliente findOne(String key) {
        return null;
    }

    @Override
    public List<Cliente> listAll() {
        return null;
    }

    @Override
    public boolean update(Cliente obj) {
        return false;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    @Override
    public Optional<Cliente> findByCpf(String cpf) {
        return Optional.empty();
    }
}
