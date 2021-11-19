package ifsp.edu.usecases.pedido;

import ifsp.edu.model.Endereco;
import ifsp.edu.model.Pedido;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class EmitirDescritivoVendaUseCase {

    private PedidoDAO dao;

    public EmitirDescritivoVendaUseCase(PedidoDAO dao) {
        this.dao = dao;
    }

    public Pedido emitirDescritivo(Pedido pedido){
        Validator<Pedido> validator = new PedidoValidator();
        Notification notification = validator.validate(pedido);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        return dao.findOne(pedido.getId());
    }
}
