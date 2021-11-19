package ifsp.edu.usecases.pedido;

import ifsp.edu.model.Pedido;
import ifsp.edu.utils.EntidadeNaoEncontradaException;

public class RemoverPedidoUseCase {
    private PedidoDAO dao;

    public RemoverPedidoUseCase(PedidoDAO dao) {
        this.dao = dao;
    }

    public boolean delete(Pedido pedido){
        if(pedido == null && dao.findById(pedido.getId()).isEmpty()){
            throw new EntidadeNaoEncontradaException("Pedido não encontrado");
        }
        return dao.delete(pedido.getId());
    }

    public boolean delete(Integer key){
        if( dao.findById(key).isEmpty() && key == null){
            throw new EntidadeNaoEncontradaException("Pedido não encontrado");
        }
        return dao.delete(key);
    }
}
