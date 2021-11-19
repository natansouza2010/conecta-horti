package ifsp.edu.usecases.pedido;

import ifsp.edu.model.Endereco;
import ifsp.edu.model.Pedido;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class InformarEntregaUseCase {

    private PedidoDAO dao;

    public InformarEntregaUseCase(PedidoDAO dao) {
        this.dao = dao;
    }

    public boolean infEntrega(Pedido pedido, String endereco){
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

        if(endereco == null){
            throw new EntidadeNaoEncontradaException("Endereço inexistente");
        }
        pedido.setEndereco(endereco);
        return dao.atualizarStatusPedido(pedido);
    }
}
