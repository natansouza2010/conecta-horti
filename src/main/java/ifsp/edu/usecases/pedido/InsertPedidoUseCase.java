package ifsp.edu.usecases.pedido;

import ifsp.edu.model.Pedido;
import ifsp.edu.usecases.pedido.PedidoDAO;
import ifsp.edu.usecases.pedido.PedidoValidator;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class InsertPedidoUseCase {
    private PedidoDAO dao;

    public InsertPedidoUseCase(PedidoDAO dao) {
        this.dao = dao;
    }

    public boolean insert(Pedido pedido){
        Validator<Pedido> validator = new PedidoValidator();
        Notification notification = validator.validate(pedido);

        if(notification.hasErrors()){
            throw new IllegalArgumentException(notification.errorMessage());
        }
        Integer id = pedido.getId();
        if(dao.findById(id).isPresent()){
            throw new EntidadeExistenteException("Id já existente");
        }
        return dao.insert(pedido);
    }
}
