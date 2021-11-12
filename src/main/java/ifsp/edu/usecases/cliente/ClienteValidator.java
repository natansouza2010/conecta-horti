package ifsp.edu.usecases.cliente;

import ifsp.edu.model.Cliente;
import ifsp.edu.model.Produto;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class ClienteValidator extends Validator <Cliente> {

    @Override
    public Notification validate(Cliente Cliente) {
        Notification notification = new Notification();

        if(Cliente == null){
            notification.addError("Cliente é nulo");
            return notification;
        }
        if(nullOrEmpty(Cliente.getCpf()))
            notification.addError("CPF do cliente é nulo ou vazio");

        if(nullOrEmpty(Cliente.getNome()))
            notification.addError("Nome do cliente é nula ou vazia");

        if(nullOrEmpty(Cliente.getEndereco()))
            notification.addError("Endereço do cliente é nulo ou vazio");

        if(nullOrEmpty(Cliente.getTelefone1()))
            notification.addError("Telefone do cliente é nulo ou vazio");

        return notification;
    }
}
