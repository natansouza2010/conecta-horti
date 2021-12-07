package ifsp.edu.controller.pedidos;

import ifsp.edu.enums.FormaDePagamento;
import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.*;
import ifsp.edu.model.Pedido;
import ifsp.edu.repository.ClienteRepository;
import ifsp.edu.repository.PedidoRepository;
import ifsp.edu.repository.PedidoRepository;
import ifsp.edu.repository.ProdutoRepository;
import ifsp.edu.sqlitedao.ClienteDAOImpl;
import ifsp.edu.sqlitedao.PedidoDAOImpl;
import ifsp.edu.sqlitedao.ProdutoDAOImpl;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.usecases.pedido.AtualizarStatusPedidoUseCase;
import ifsp.edu.usecases.pedido.InsertPedidoUseCase;
import ifsp.edu.usecases.pedido.PedidoDAO;
import ifsp.edu.usecases.pedido.RemoverPedidoUseCase;
import ifsp.edu.usecases.produto.ProdutoDAO;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class CtlrSubmenuPedidos {

    @FXML TableView<Pedido> table;
    @FXML Label txtNomeClientePedido;
    @FXML Label txtCpfClientePedido;
    @FXML Label txtEnderecoClientePedido;
    @FXML Label txtTel1ClientePedido;
    @FXML Label txtTel2ClientePedido;
    @FXML Button btnVoltar;
    @FXML Button btnAtualizaStatus;
    @FXML Button btnAdicionarPedido;
    @FXML TableColumn colIdPedido;
    @FXML TableColumn colStatusPedido;
    @FXML TableColumn colDataPedido;
    @FXML TableColumn colValorPedido;

    ObservableList<Pedido> pedidos;
    ObservableList<Cliente> clientes;
    ObservableList<Produto> produtos;

    //dao | usecases
    PedidoDAO pedidoDAO = new PedidoDAOImpl();
    ClienteDAO clienteDAO = new ClienteDAOImpl();
    ProdutoDAO produtoDAO = new ProdutoDAOImpl();
    InsertPedidoUseCase insertPedidoUseCase;
    RemoverPedidoUseCase removerPedidoUseCase;
    AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    public void initialize(){
        colDataPedido.setCellValueFactory(new PropertyValueFactory<Pedido, String>("dataPedido"));
        colIdPedido.setCellValueFactory(new PropertyValueFactory<Pedido, String>("id"));
        colStatusPedido.setCellValueFactory(new PropertyValueFactory<Pedido, String>("status"));
        colValorPedido.setCellValueFactory(new PropertyValueFactory<Pedido, Double >("valor"));

        //loadData();

        pedidos = FXCollections.observableArrayList();
        clientes = FXCollections.observableArrayList();
        produtos = FXCollections.observableArrayList();

        loadTable();
        table.setItems(pedidos);
    }

    /*private void loadData() {
        List<Cliente> clientes = new ArrayList<>(clienteDAO.listAll());

        List<Produto> produtos = new ArrayList<>(produtoDAO.listAll());

        Item i1 = new Item();
        i1.setQuantidade(2);
        i1.setProduto(produtos.get(0));
        i1.setValorVenda(i1.calculaValor());

        Item i2 = new Item();
        i2.setQuantidade(3);
        i2.setProduto(produtos.get(0));
        i2.setValorVenda(i2.calculaValor());

        List<Item> items = new ArrayList<>();
        items.add(i1);
        items.add(i2);

        Double valorTotal = 0.0;
        for (Item item : items) {
            valorTotal += item.getProduto().getValorVenda() * item.getQuantidade();

        }

        Item i3 = new Item();
        i3.setQuantidade(1);
        i3.setProduto(produtos.get(2));
        i3.setValorVenda(i3.calculaValor());

        Item i4 = new Item();
        i4.setQuantidade(1);
        i4.setProduto(produtos.get(1));
        i4.setValorVenda(i3.calculaValor());

        List<Item> items2 = new ArrayList<>();

        items2.add(i3);
        items2.add(i4);

        Double valorTotal2 = 0.0;
        for (Item item : items2) {
            valorTotal2 += item.getProduto().getValorVenda() * item.getQuantidade();

        }

        LocalDate datainicial = LocalDate.of(2021,11,1);
        LocalDate datainicial2 = LocalDate.of(2021,11,2);

    }
*/
    private void loadTable(){
        List<Cliente> clients = new ArrayList<>(clienteDAO.listAll());
        List<Produto> prods = new ArrayList<>(produtoDAO.listAll());
        List<Pedido> peds = new ArrayList<>(pedidoDAO.listAll());
        pedidos = FXCollections.observableArrayList(peds);
        produtos = FXCollections.observableArrayList(prods);
        clientes = FXCollections.observableArrayList(clients);
    }

    private void reloadTable(){
        pedidos.clear();
        loadTable();
        table.setItems(pedidos);
    }

    public void atualizarStatus(ActionEvent actionEvent) {
        Pedido p = table.getSelectionModel().getSelectedItem();
        atualizarStatusPedidoUseCase = new AtualizarStatusPedidoUseCase(pedidoDAO);
        atualizarStatusPedidoUseCase.updateStatus(p, StatusPedido.PAGO);
        reloadTable();
    }

    public void removePedido(ActionEvent actionEvent){
        Pedido p = table.getSelectionModel().getSelectedItem();
        removerPedidoUseCase = new RemoverPedidoUseCase(pedidoDAO);
        removerPedidoUseCase.delete(p.getId());
        reloadTable();
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

    public void transferDataToLabels(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 1){
            Pedido p = table.getSelectionModel().getSelectedItem();
            if(p != null){
                txtCpfClientePedido.setText(p.getCliente().getCpf());
                txtNomeClientePedido.setText(p.getCliente().getNome());
                txtEnderecoClientePedido.setText(p.getEndereco());
                txtTel1ClientePedido.setText(p.getCliente().getTelefone1());
                txtTel2ClientePedido.setText(p.getCliente().getTelefone2());
            }
        }
    }

    public void voltar(ActionEvent actionEvent) {
        close();
    }

    private void close(){
        Stage stage = (Stage) btnAtualizaStatus.getScene().getWindow();
        stage.close();
    }

}
