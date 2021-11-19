package ifsp.edu.controller.pedidos;

import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Pedido;
import ifsp.edu.model.Pedido;
import ifsp.edu.repository.PedidoRepository;
import ifsp.edu.repository.PedidoRepository;
import ifsp.edu.usecases.pedido.PedidoDAO;
import ifsp.edu.view.clientes.WindowCadastroClientes;
import ifsp.edu.view.pedidos.WindowCadastroPedidos;
import ifsp.edu.view.pedidos.WindowSubmenuPedidos;
import ifsp.edu.view.principal.WindowPrincipal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CtlrSubmenuPedidos {

    @FXML TableView<Pedido> table;
    @FXML Label txtNomeClientePedido;
    @FXML Label txtCpfClientePedido;
    @FXML Label txtEnderecoClientePedido;
    @FXML Label txtTel1ClientePedido;
    @FXML Label txtEnderecoCliente;
    @FXML Button btnVoltar;
    @FXML Button btnAtualizaStatus;
    @FXML Button btnAdicionarPedido;
    @FXML TableColumn colIdPedido;
    @FXML TableColumn colStatusPedido;
    @FXML TableColumn colDataPedido;

    ObservableList<Pedido> pedidos;

    public void initialize(){
        colDataPedido.setCellValueFactory(new PropertyValueFactory<Pedido, String>("dataPedido"));
        colIdPedido.setCellValueFactory(new PropertyValueFactory<Pedido, String>("id"));
        colStatusPedido.setCellValueFactory(new PropertyValueFactory<Pedido, String>("status"));

        pedidos = FXCollections.observableArrayList();
        PedidoDAO dao = new PedidoRepository();


        loadTable();
    }

    private void loadTable(){
        PedidoRepository dao = new PedidoRepository();
        List<Pedido> forn = new ArrayList<>(dao.listAll());
        pedidos = FXCollections.observableArrayList(forn);
    }

    private void reloadTable(){
        pedidos.clear();
        loadTable();
        table.setItems(pedidos);
    }

    public void voltarMenu(ActionEvent actionEvent) {
       close();
    }

    public void atualizarStatus(ActionEvent actionEvent) {

    }

    public void cadastrarPedido(ActionEvent actionEvent) {
        WindowCadastroPedidos window = new WindowCadastroPedidos();
        try {
            window.show();
            reloadTable();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    private void close(){
        Stage stage = (Stage) btnAtualizaStatus.getScene().getWindow();
        stage.close();
    }
}
