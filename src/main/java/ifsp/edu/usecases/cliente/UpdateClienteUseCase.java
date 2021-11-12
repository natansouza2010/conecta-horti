package ifsp.edu.usecases.cliente;

import ifsp.edu.model.Cliente;
import ifsp.edu.model.Produto;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.usecases.produto.ProdutoValidator;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class UpdateClienteUseCase {

    private ClienteDAO dao;

    public UpdateClienteUseCase(ClienteDAO dao) {
        this.dao = dao;
    }

    public boolean update(Cliente cliente){
        Validator<Cliente> validator = new ClienteValidator();
        Notification notification = validator.validate(cliente);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        String cpf = cliente.getCpf();
        if(dao.findByCpf(cpf).isEmpty()){
            throw new EntidadeNaoEncontradaException(notification.errorMessage());
        }

        return dao.update(cliente);
    }
}
