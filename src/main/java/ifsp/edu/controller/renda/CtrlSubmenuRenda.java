package ifsp.edu.controller.renda;

import ifsp.edu.model.Cliente;
import ifsp.edu.model.Renda;
import ifsp.edu.repository.PedidoRepository;
import ifsp.edu.repository.ProdutoRepository;
import ifsp.edu.repository.RendaRepository;
import ifsp.edu.usecases.pedido.PedidoDAO;
import ifsp.edu.usecases.produto.DeleteProdutoUseCase;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.usecases.renda.DeleteRendaUseCase;
import ifsp.edu.usecases.renda.InserirRendaUseCase;
import ifsp.edu.usecases.renda.RendaDAO;
import ifsp.edu.view.renda.WindowCadastroRenda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CtrlSubmenuRenda {

    @FXML
    TableView<Renda> tableRenda;

    @FXML TableColumn<Renda, Integer> colIdRenda;
    @FXML TableColumn<Renda, LocalDate> conDataInicialRenda;
    @FXML TableColumn<Renda, LocalDate> colDataFinalRenda;
    @FXML TableColumn<Renda, Double> colLucroRenda;
    @FXML TableColumn<Renda, Double> colDespesaRenda;

    ObservableList<Renda> rendas;

    //RendaRepository rendaRepository = new RendaRepository();
    RendaDAO rendaDAO = new RendaRepository();
    InserirRendaUseCase inserirRendaUseCase;
    DeleteRendaUseCase deleteRendaUseCase;

    public void initialize(){
        colIdRenda.setCellValueFactory(new PropertyValueFactory<Renda,Integer>("id"));
        conDataInicialRenda.setCellValueFactory(new PropertyValueFactory<Renda, LocalDate>("dataInicial"));
        colDataFinalRenda.setCellValueFactory(new PropertyValueFactory<Renda,LocalDate>("dataFinal"));
        colLucroRenda.setCellValueFactory(new PropertyValueFactory<Renda,Double>("lucroObtido"));
        colDespesaRenda.setCellValueFactory(new PropertyValueFactory<Renda,Double>("despesa"));
        rendas = FXCollections.observableArrayList();
//        PedidoDAO daoPedido = new PedidoRepository();
//        ProdutoDAO daoProduto = new ProdutoRepository();
//        RendaDAO dao = new RendaRepository();
//        inserirRendaUseCase = new InserirRendaUseCase(dao);
//        Renda r1 = new Renda(10,LocalDate.of(2021,11,2),LocalDate.of(2021,11,3),daoPedido.listAll(), daoProduto.listAll());
//        inserirRendaUseCase.insert(r1);
//        System.out.println(r1.toString());
        loadTable();
        tableRenda.setItems(rendas);
    }

    private void loadTable() {
        List<Renda> rendasArray = new ArrayList<>(rendaDAO.listAll());
        rendas = FXCollections.observableArrayList(rendasArray);
    }

    private void reloadTable(){
        rendas.clear();
        loadTable();
        tableRenda.setItems(rendas);
        System.out.println(rendas);
    }

    public void criarRenda(ActionEvent actionEvent) {
        WindowCadastroRenda window = new WindowCadastroRenda();
        try{
            window.show();
            reloadTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removerRenda(ActionEvent actionEvent) {
        deleteRendaUseCase = new DeleteRendaUseCase(rendaDAO);
        Renda r = tableRenda.getSelectionModel().getSelectedItem();
        if(r != null){
            deleteRendaUseCase.delete(r.getId());
            reloadTable();
        }
    }
    public void voltar(ActionEvent actionEvent) {
        close();
    }

    public void close(){
        Stage stage = (Stage) tableRenda.getScene().getWindow();
        stage.close();
    }

}
