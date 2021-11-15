package ifsp.edu.usecases.pedido;

import ifsp.edu.model.Pedido;
import ifsp.edu.model.Produto;
import ifsp.edu.usecases.produto.ProdutoValidator;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class RealizarPedidoUseCase {

    private PedidoDAO dao;

    public RealizarPedidoUseCase(PedidoDAO dao) {
        this.dao = dao;
    }

    public boolean insert(Pedido pedido){
        Validator<Pedido> validator = new PedidoValidator();
        Notification notification = validator.validate(pedido);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        Integer id = pedido.getId();
        if(dao.findById(id).isPresent()){
            throw new EntidadeExistenteException(notification.errorMessage());
        }

        String cpf = pedido.getCliente().getCpf();
        if(dao.findClienteByCpf(cpf).isEmpty()){
            throw new EntidadeNaoEncontradaException("CPF do cliente não encontrado.");
        }
        return dao.insert(pedido);
    }
}
