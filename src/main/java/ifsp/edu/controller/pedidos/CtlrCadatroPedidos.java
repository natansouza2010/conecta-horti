package ifsp.edu.controller.pedidos;

import ifsp.edu.enums.FormaDePagamento;
import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.*;
import ifsp.edu.repository.ClienteRepository;
import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.repository.PedidoRepository;
import ifsp.edu.repository.ProdutoRepository;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.usecases.pedido.InsertPedidoUseCase;
import ifsp.edu.usecases.pedido.PedidoDAO;
import ifsp.edu.usecases.pedido.UpdatePedidoUseCase;
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
import java.util.stream.Collectors;

public class CtlrCadatroPedidos {

    @FXML ChoiceBox<String> cbProdutos;
    @FXML ChoiceBox<String> cbCpfCliente;
    @FXML ChoiceBox<String> cbPagamento;
    @FXML TextField txtQuantidadeProduto;
    @FXML Label txtValorTotal;
    @FXML TableColumn colIdProduto;
    @FXML TableColumn colQuantidadeProduto;
    @FXML TableColumn colValorProduto;
    @FXML Button btnAddProduto;

    @FXML TableView<Pedido> table;
    ObservableList produtosDoPedido;


    private Pedido pedido;
    private InsertPedidoUseCase insertPedidoUseCase;
    private UpdatePedidoUseCase updatePedidoUseCase;
    ArrayList<Item> listaItens = new ArrayList();


    //CLIENTES
    ClienteRepository daoCliente = new ClienteRepository();
    List<Cliente> clienteArrayList = new ArrayList<>(daoCliente.listAll());
    ObservableList<String> clientes = FXCollections.observableArrayList(clienteArrayList.stream().map(c -> c.getCpf()).collect(Collectors.toList()));
    ObservableList<String> cpfClientesCadastrados = FXCollections.observableArrayList(clientes);

    //FORNECEDORES
    ProdutoRepository daoProdutos = new ProdutoRepository();
    List<Produto> produtosArrayList = new ArrayList<>(daoProdutos.listAll());
    ObservableList<String> produtos = FXCollections.observableArrayList(produtosArrayList.stream().map(c -> c.getNome()).collect(Collectors.toList()));
    ObservableList<String> produtosCadastrados = FXCollections.observableArrayList(produtos);

    //FORMA PAGAMENTO
    ObservableList<String> formaPagamento = FXCollections.observableArrayList("DINHEIRO","CARTAO");

    @FXML
    private void initialize(){
        cbCpfCliente.setItems(cpfClientesCadastrados);
        cbProdutos.setItems(produtosCadastrados);
        cbPagamento.setItems(formaPagamento);
        colValorProduto.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valorVenda"));
        colQuantidadeProduto.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantidade"));
        colIdProduto.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("id"));

        produtosDoPedido = FXCollections.observableArrayList();

    }

    private PedidoDTO getPedidoFromView(){
        String cpfCliente = String.valueOf(cbCpfCliente.getSelectionModel().getSelectedItem());
        String nomeProduto = String.valueOf(cbProdutos.getSelectionModel().getSelectedItem());
        Cliente c = daoCliente.findOne(cpfCliente);
        Produto p = daoProdutos.findByNome(nomeProduto);

        Integer quantidadeProduto = Integer.valueOf(txtQuantidadeProduto.getText());
        FormaDePagamento formaDePagamento = FormaDePagamento.valueOf(cbPagamento.getSelectionModel().getSelectedItem());

        Item i = new Item(quantidadeProduto,p);
        PedidoDTO pedido = new PedidoDTO(c, p, quantidadeProduto, formaDePagamento);
        listaItens.add(i);

        return pedido;
    }

    private void loadTable(){
        FXCollections.observableArrayList(listaItens);
    }

    public void btnAdicionarProdutoToTable(ActionEvent actionEvent) {
        loadTable();

    }

    public Pedido criarPedido(PedidoDTO dto){
        Double valorTotalPedido = listaItens.stream().mapToDouble(c->c.calculaValor()).sum();
        Pedido pedidoFinal = new Pedido(1,dto.getCliente(), listaItens, valorTotalPedido,LocalDate.now(),StatusPedido.A_PAGAR,dto.getCliente().getEndereco(), dto.getFormaDePagamento() );
        System.out.println(pedidoFinal.getCliente().getNome());
        return pedidoFinal;
    }

    public void saveOrUpdate(ActionEvent actionEvent) {
        saveOrUpdate();
        close();
    }
    public void btnVoltar(ActionEvent actionEvent) {
        close();
    }

    public void setPedidosToView(Pedido p) {
        pedido=p;
        p.setStatus(StatusPedido.PAGO);

    }

    public void saveOrUpdate() throws RuntimeException{
        Pedido a = criarPedido(getPedidoFromView());
        if (pedido == null && a != null ) {
            save(a);
        } else if (pedido != null && a != null ) {
            update(a);
        }

    }
    private void update(Pedido f) {
        PedidoDAO dao = new PedidoRepository();
        updatePedidoUseCase = new UpdatePedidoUseCase(dao);
        updatePedidoUseCase.update(f);
    }
    private void save(Pedido f) {
        PedidoDAO dao = new PedidoRepository();
        insertPedidoUseCase = new InsertPedidoUseCase(dao);
        insertPedidoUseCase.insert(f);
    }
    private void close(){
        Stage stage = (Stage) btnAddProduto.getScene().getWindow();
        stage.close();
    }
}
