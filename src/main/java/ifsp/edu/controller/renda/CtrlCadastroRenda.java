package ifsp.edu.controller.renda;

import ifsp.edu.model.CompraProduto;
import ifsp.edu.model.Pedido;
import ifsp.edu.model.Produto;
import ifsp.edu.model.Renda;
import ifsp.edu.repository.PedidoRepository;
import ifsp.edu.repository.ProdutoRepository;
import ifsp.edu.repository.RendaRepository;
import ifsp.edu.sqlitedao.CompraProdutoDAOImpl;
import ifsp.edu.sqlitedao.PedidoDAOImpl;
import ifsp.edu.sqlitedao.ProdutoDAOImpl;
import ifsp.edu.usecases.compraproduto.CompraProdutoDAO;
import ifsp.edu.usecases.pedido.PedidoDAO;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.usecases.renda.InserirRendaUseCase;
import ifsp.edu.usecases.renda.RendaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CtrlCadastroRenda {
    @FXML
    DatePicker dpDataInicial;
    @FXML
    DatePicker dpDataFinal;
    @FXML
    Button btnCriarRenda;
    @FXML
    Button btnVoltar;

    InserirRendaUseCase inserirRendaUseCase;
    PedidoDAO pedidoDAO = new PedidoDAOImpl();
//    ProdutoDAO produtoDAO = new ProdutoDAOImpl();
    CompraProdutoDAOImpl comprasDAO = new CompraProdutoDAOImpl();

    static Integer cont = 0;

    public void criarNovaRenda(ActionEvent actionEvent) {
        Renda r = getRendaFromView();
        if(r != null) {
            save(r);
            close();
        }
    }

    private void save(Renda r) {
        RendaDAO daoRenda = new RendaRepository();
        inserirRendaUseCase = new InserirRendaUseCase(daoRenda);
        inserirRendaUseCase.insert(r);
       cont++;
    }

    public Renda getRendaFromView(){
        if( dpDataInicial.getValue() != null && dpDataFinal.getValue() != null){


            LocalDate dataInicial = dpDataInicial.getValue();
            LocalDate dataFinal = dpDataFinal.getValue();
            List<CompraProduto> compras = new ArrayList<>(comprasDAO.findByPeriodoPago(dataInicial,dataFinal));
            List<Pedido> pedidoList = new ArrayList<>(pedidoDAO.findByPeriodoPago(dataInicial,dataFinal));
            Renda renda = new Renda();
            renda.setId(cont);
            renda.setDataInicial(dataInicial);
            renda.setDataFinal(dataFinal);
            renda.setPedidos(pedidoList);
            renda.setCompras(compras);
            renda.calculaLucro();
            return renda;
        }
        return null;
    }

    private void close(){
        Stage stage = (Stage) dpDataFinal.getScene().getWindow();
        stage.close();
    }

    public void voltar(ActionEvent actionEvent) {
        close();
    }
}
