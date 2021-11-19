package ifsp.edu.usecases.pedido;

import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Pedido;
import ifsp.edu.usecases.pedido.PedidoDAO;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class UpdatePedidoUseCase {
    private PedidoDAO dao;

    public UpdatePedidoUseCase(PedidoDAO dao) {
        this.dao = dao;
    }

    public boolean update(Pedido pedido){
        Validator<Pedido> validator = new PedidoValidator();
        Notification notification = validator.validate(pedido);

        if(notification.hasErrors()){
            throw new IllegalArgumentException(notification.errorMessage());
        }
        if(dao.findById(pedido.getId()).isEmpty()){
            throw new EntidadeNaoEncontradaException("Cnpj não existe");
        }
        System.out.println("Dfghfjgkhliykujyhdtgsrfertdhfytdghfdtrgfefb gtr54rtgbcfvd");
        return dao.update(pedido);

    }
}
