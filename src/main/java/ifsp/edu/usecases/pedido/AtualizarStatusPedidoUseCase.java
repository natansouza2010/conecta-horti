package ifsp.edu.usecases.pedido;

import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.Pedido;
import ifsp.edu.repository.ClienteRepository;
import ifsp.edu.sqlitedao.ClienteDAOImpl;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class AtualizarStatusPedidoUseCase {

    private PedidoDAO dao;
    private ClienteDAO daoCliente;

    public AtualizarStatusPedidoUseCase(PedidoDAO dao) {
        this.dao = dao;
    }

    public boolean updateStatus(Pedido pedido, StatusPedido statusPedido){
        Validator<Pedido> validator = new PedidoValidator();
        Notification notification = validator.validate(pedido);
        daoCliente = new ClienteDAOImpl();

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        Integer id = pedido.getId();
        if(!dao.findById(id).isPresent()){
            throw new EntidadeExistenteException(notification.errorMessage());
        }

        String cpf = pedido.getCliente().getCpf();
        if(daoCliente.findByCpf(cpf).isEmpty()){
            throw new EntidadeNaoEncontradaException("CPF do cliente não encontrado.");
        }

        if(statusPedido == null){
            throw new EntidadeNaoEncontradaException("Status inexistente");
        }

        pedido.setStatus(StatusPedido.PAGO);
        return dao.atualizarStatusPedido(pedido);
    }
}
