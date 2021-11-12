package ifsp.edu.usecases.cliente;

import ifsp.edu.model.Cliente;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class InserirClienteUseCase {

    private ClienteDAO dao;

    public InserirClienteUseCase(ClienteDAO dao) {
        this.dao = dao;
    }

    public boolean insert(Cliente cliente){
        Validator<Cliente> validator = new ClienteValidator();
        Notification notification = validator.validate(cliente);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        String cpf = cliente.getCpf();
        if(dao.findByCpf(cpf).isPresent()){
            throw new EntidadeExistenteException(notification.errorMessage());
        }

        return dao.insert(cliente);
    }
}
