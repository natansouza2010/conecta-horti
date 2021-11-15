package ifsp.edu.usecases.pedido;

import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.Pedido;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class VisualizarHistoricoDePedidosUseCase {

    private PedidoDAO dao;

    public VisualizarHistoricoDePedidosUseCase(PedidoDAO dao) {
        this.dao = dao;
    }

    public List<Pedido> getTodosPedidos(){
        return dao.listAll();
    }

    public List<Pedido> getPedidoByDate(LocalDate data){
        if(data != null){
            return dao.findByDate(data);
        }
        return null;
    }

    public List<Pedido> getPedidoByNomeCliente(String nome){
        if(nome != null){
            return dao.findByNomeCliente(nome);
        }
        return null;
    }

    public List<Pedido> getPedidoByStatus(StatusPedido status){
        if(status != null){
            return dao.findByStatus(status);
        }
        return null;
    }

//    private List<Pedido> percorrerPedidosPelaData(LocalDate dataPedido){
//        List<Pedido> pedidos = null;
//        for (Pedido pedido: getTodosPedidos()) {
//            if(pedido.getDataPedido() == dataPedido)
//            pedidos.add(pedido);
//        }
//        return pedidos;
//    }
}
