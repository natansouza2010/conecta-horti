package ifsp.edu.usecases.cliente;

import ifsp.edu.model.Cliente;
import ifsp.edu.model.Produto;
import ifsp.edu.usecases.produto.ProdutoDAO;

import java.util.List;
import java.util.Optional;

public class FindClienteUseCase {

    private ClienteDAO dao;

    public FindClienteUseCase(ClienteDAO dao) {
        this.dao = dao;
    }

    public Optional<Cliente> findOne(String cpf){
        if(cpf == null){
            throw new IllegalArgumentException("CPF não pod ser nulo.");
        }
        return dao.findByCpf(cpf);
    }

    public List<Cliente> findAll(){
        return dao.listAll();
    }
}
