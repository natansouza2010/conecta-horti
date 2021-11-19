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
    @FXML TableColumn<Item, Produto> colProduto;
    @FXML TableColumn<Item, Integer> colQuantidadeProduto;
    @FXML TableColumn<Item ,Double> colValorProduto;
    @FXML Button btnAddProduto;

    @FXML TableView<Item> table;
    ObservableList produtosDoPedido;


    private Pedido pedido;
    private InsertPedidoUseCase insertPedidoUseCase;
    private UpdatePedidoUseCase updatePedidoUseCase;
    ArrayList<Item> listaItens = new ArrayList();
    ClienteRepository daoCliente = new ClienteRepository();
    ProdutoRepository daoProdutos = new ProdutoRepository();

    static Integer cont=0;




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

        List<Cliente> clienteArrayList = new ArrayList<>(daoCliente.listAll());
        ObservableList<String> clientes = FXCollections.observableArrayList(clienteArrayList.stream().map(c -> c.getCpf()).collect(Collectors.toList()));
        ObservableList<String> cpfClientesCadastrados = FXCollections.observableArrayList(clientes);

        //FORNECEDORES

        List<Produto> produtosArrayList = new ArrayList<>(daoProdutos.listAll());
        ObservableList<String> produtos = FXCollections.observableArrayList(produtosArrayList.stream().map(c -> c.getNome()).collect(Collectors.toList()));
        ObservableList<String> produtosCadastrados = FXCollections.observableArrayList(produtos);

        //FORMA PAGAMENTO
        ObservableList<String> formaPagamento = FXCollections.observableArrayList("DINHEIRO","CARTAO");
        cbCpfCliente.setItems(cpfClientesCadastrados);
        cbProdutos.setItems(produtosCadastrados);
        cbPagamento.setItems(formaPagamento);

    }


    private PedidoDTO getPedidoFromView(){
        String cpfCliente = String.valueOf(cbCpfCliente.getSelectionModel().getSelectedItem());
        String nomeProduto = String.valueOf(cbProdutos.getSelectionModel().getSelectedItem());
        Cliente c = daoCliente.findOne(cpfCliente);
        Produto p = daoProdutos.findByNome(nomeProduto);

//        Item item = new Item();
//        Integer quantidadeProduto = Integer.valueOf(txtQuantidadeProduto.getText());
//        item.setQuantidade(quantidadeProduto);
//        item.setProduto(p);
//        item.setValorVenda(p.getValorVenda());
        FormaDePagamento formaDePagamento = FormaDePagamento.valueOf(cbPagamento.getSelectionModel().getSelectedItem());

//        pedido.getItems().add(item);
//        pedido.setValor(pedido.calculaTotalPedido());
//        Item i = new Item(quantidadeProduto,p);
         PedidoDTO pedido = new PedidoDTO(c,listaItens,formaDePagamento);


        return pedido;
    }

////    private void loadTable(){
//        System.out.println(pedido.getItems());
//        table.setItems(FXCollections.observableArrayList(pedido.getItems()));
//        txtValorTotal.setText(String.valueOf(pedido.getValor()));
//    }

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
//        Pedido pedido = getPedidoFromView();
//        System.out.println(pedido.getItems().size());
//        System.out.println(pedido.getItems());
//        table.setItems(FXCollections.observableArrayList(pedido.getItems()));
//        txtValorTotal.setText(String.valueOf(pedido.getValor()));
    }

    private Double getValorTotalFromList() {
        Double sum = 0.0;
        for (Item i : listaItens) {
            sum += i.getProduto().getValorVenda() * i.getQuantidade();
        }
        return sum;
    }

    public Pedido criarPedido(PedidoDTO pedido){
//        Double valorTotalPedido = listaItens.stream().mapToDouble(c->c.calculaValor()).sum();
        Pedido pedidoFinal = new Pedido(cont,pedido.getCliente(), pedido.getItems(), pedido.calculaTotalPedido(),LocalDate.now(),StatusPedido.A_PAGAR,pedido.getCliente().getEndereco(), pedido.getFormaDePagamento() );
        System.out.println(pedido.getItems().toString());
        System.out.println(pedidoFinal.toString());
        cont++;
        return pedidoFinal;
    }

    public void saveOrUpdate(ActionEvent actionEvent) {
        saveOrUpdate();
        close();
    }
    public void btnVoltar(ActionEvent actionEvent) {
        close();
    }

    public void changeStatusPedido(Pedido p) {
        pedido=p;
        p.setStatus(StatusPedido.PAGO);

    }

    public void saveOrUpdate() throws RuntimeException{
        Pedido a = criarPedido(getPedidoFromView());
        if (pedido == null && a != null ) {
            save(a);
        }

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
