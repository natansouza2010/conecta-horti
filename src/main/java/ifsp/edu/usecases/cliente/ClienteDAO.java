package ifsp.edu.usecases.cliente;

import ifsp.edu.dao.DAO;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Produto;

import java.util.Optional;

public interface ClienteDAO extends DAO<Cliente, String> {
    Optional<Cliente> findByCpf(String cpf);
}
