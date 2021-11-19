package ifsp.edu.usecases.pedido;

import ifsp.edu.model.Pagamento;
import ifsp.edu.model.Pedido;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class EscolherMetodoPagamentoUseCase {

    private PedidoDAO dao;

    public EscolherMetodoPagamentoUseCase(PedidoDAO dao) {
        this.dao = dao;
    }

    public boolean escolherMetodo(Pedido pedido, Pagamento pagamento){
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

        if(pagamento == null){
            throw new EntidadeNaoEncontradaException("Método de pagamento não encontrado");
        }
        pedido.setPagamento(pagamento);
        return dao.escolherMetodoPagamento(pedido);
    }
}
