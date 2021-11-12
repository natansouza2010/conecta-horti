package ifsp.edu.usecases.cliente;

import ifsp.edu.model.Cliente;
import ifsp.edu.model.Produto;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.utils.EntidadeNaoEncontradaException;

public class DeleteClienteUseCase {

    private ClienteDAO dao;

    public DeleteClienteUseCase(ClienteDAO dao) {
        this.dao = dao;
    }

    public boolean delete(String cpf){
        if(cpf == null || dao.findByCpf(cpf).isEmpty()){
            throw new EntidadeNaoEncontradaException("CPF nulo ou inexistente.");
        }
        return dao.delete(cpf);
    }

    public boolean delete(Cliente cliente){
        if(cliente == null || dao.findOne(cliente.getCpf()) == null){
            throw new EntidadeNaoEncontradaException("Cliente não encontrado.");
        }
        return dao.delete(cliente.getCpf());
    }
}
