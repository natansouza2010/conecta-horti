package ifsp.edu.controller.pedidos;

import ifsp.edu.enums.FormaDePagamento;
import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.*;
import ifsp.edu.repository.ClienteRepository;
import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.repository.PedidoRepository;
import ifsp.edu.repository.ProdutoRepository;
import ifsp.edu.sqlitedao.ClienteDAOImpl;
import ifsp.edu.sqlitedao.PedidoDAOImpl;
import ifsp.edu.sqlitedao.ProdutoDAOImpl;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.usecases.cliente.FindClienteUseCase;
import ifsp.edu.usecases.pedido.InsertPedidoUseCase;
import ifsp.edu.usecases.pedido.PedidoDAO;
import ifsp.edu.usecases.pedido.UpdatePedidoUseCase;
import ifsp.edu.usecases.produto.FindProdutoUseCase;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.view.pedidos.WindowCadastroPedidos;
import ifsp.edu.view.pedidos.WindowSubmenuPedidos;
import ifsp.edu.view.principal.WindowPrincipal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class CtlrCadatroPedidos {

    @FXML ChoiceBox<String> cbProdutos;
    @FXML ChoiceBox<String> cbCpfCliente;
    @FXML ChoiceBox<String> cbPagamento;
    @FXML TextField txtQuantidadeProduto;
    @FXML Label txtValorTotal;
    @FXML TableColumn<Item, Produto> colProduto;
    @FXML TableColumn<Item, Integer> colQuantidadeProduto;
    @FXML TableColumn<Item ,Double> colValorProduto;
    @FXML Button btnAddProduto;

    @FXML TableView<Item> table;
    ObservableList produtosDoPedido;

    private InsertPedidoUseCase insertPedidoUseCase;
    private FindClienteUseCase findClienteUseCase;
    private FindProdutoUseCase findProdutoUseCase;

    ArrayList<Item> listaItens = new ArrayList();
    PedidoDAO pedidoDAO = new PedidoDAOImpl();
    ClienteDAO daoCliente = new ClienteDAOImpl();
    ProdutoDAO daoProdutos = new ProdutoDAOImpl();

    static Integer cont=3;

    @FXML
    private void initialize(){
        colValorProduto.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
        colQuantidadeProduto.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        addDataInCb();
        produtosDoPedido = FXCollections.observableArrayList();

    }

    public void addDataInCb(){
        //CLIENTES
        List<Cliente> clienteArrayList = new ArrayList<>(daoCliente.listAll());                       //retorna apenas os cpfs
        ObservableList<String> clientes = FXCollections.observableArrayList(clienteArrayList.stream().map(c -> c.getCpf()).collect(Collectors.toList()));
        ObservableList<String> cpfClientesCadastrados = FXCollections.observableArrayList(clientes);

        //FORNECEDORES
        List<Produto> produtosArrayList = new ArrayList<>(daoProdutos.listAll());                     //retorna
        ObservableList<String> produtos = FXCollections.observableArrayList(produtosArrayList.stream().map(c -> c.getNome()).collect(Collectors.toList()));
        ObservableList<String> produtosCadastrados = FXCollections.observableArrayList(produtos);

        //FORMA PAGAMENTO
        ObservableList<String> formaPagamento = FXCollections.observableArrayList("DINHEIRO","CARTAO");
        cbCpfCliente.setItems(cpfClientesCadastrados);
        cbProdutos.setItems(produtosCadastrados);
        cbPagamento.setItems(formaPagamento);

    }

    private Pedido getPedidoFromView(){
        findClienteUseCase = new FindClienteUseCase(daoCliente);
        String cpfCliente = String.valueOf(cbCpfCliente.getSelectionModel().getSelectedItem());
        Cliente c = findClienteUseCase.findOne(cpfCliente).get();
        FormaDePagamento formaDePagamento = FormaDePagamento.valueOf(cbPagamento.getSelectionModel().getSelectedItem());
        Pedido pedido = new Pedido(cont,c,listaItens,LocalDate.now(),StatusPedido.A_PAGAR, c.getEndereco(),formaDePagamento );
        System.out.println(pedido.getItems().toString());
        System.out.println(pedido.toString());
        cont++;

        return pedido;
    }

    public void btnAdicionarProdutoToTable(ActionEvent actionEvent) {
        String nomeProduto = String.valueOf(cbProdutos.getSelectionModel().getSelectedItem());

        Produto produto = daoProdutos.findByNome(nomeProduto);
        Item item = new Item();
        item.setProduto(produto);
        item.setQuantidade( Integer.valueOf(txtQuantidadeProduto.getText()));
        item.setValorVenda(item.calculaValor());
        listaItens.add(item);
        table.setItems(FXCollections.observableArrayList(listaItens));

        Double valorTotal = getValorTotalFromList();

        txtValorTotal.setText(String.valueOf(valorTotal));
;
    }

    private Double getValorTotalFromList() {
        Double sum = 0.0;
        for (Item i : listaItens) {
            sum += i.getProduto().getValorVenda() * i.getQuantidade();
        }
        return sum;
    }

    public void savePedido() throws RuntimeException{
        Pedido a = getPedidoFromView();
        if (a !=null ) {
            save(a);
            close();
        }
    }

    private void save(Pedido f) {
        PedidoDAO dao = new PedidoDAOImpl();
        insertPedidoUseCase = new InsertPedidoUseCase(dao);
        insertPedidoUseCase.insert(f);
    }

    public void voltar(ActionEvent actionEvent) {
        close();
    }

    private void close(){
        Stage stage = (Stage) btnAddProduto.getScene().getWindow();
        stage.close();
    }



}
