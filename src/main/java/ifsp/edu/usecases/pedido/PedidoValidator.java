package ifsp.edu.usecases.pedido;

import ifsp.edu.model.Pedido;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class PedidoValidator extends Validator<Pedido> {
    @Override
    public Notification validate(Pedido pedido) {
        Notification notification = new Notification();

        if(pedido == null){
            notification.addError("Pedido é nulo");
            return notification;
        }
        if(nullOrEmpty(pedido.getId().toString()))
            notification.addError("Id do pedido é nulo ou vazio");

        if(nullOrEmpty(pedido.getItems()))
            notification.addError("Lista de itens é nula ou vazia");

        if(nullOrEmpty(pedido.getDataPedido().toString()))
            notification.addError("Instante de realização do pedido é nulo ou vazio");

        if(nullOrEmpty(pedido.getEndereco().toString()))
            notification.addError("Endereço do pedido é nulo ou vazio");

        if(nullOrEmpty(pedido.getPagamento().toString()))
            notification.addError("Método de pagamento é nulo ou vazio");

        return notification;
    }
}
