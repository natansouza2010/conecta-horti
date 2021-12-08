package ifsp.edu.controller.compraproduto;

import ifsp.edu.enums.FormaDePagamento;
import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.CompraProduto;
import ifsp.edu.model.Pedido;
import ifsp.edu.model.Produto;
import ifsp.edu.repository.CompraProdutoRepository;
import ifsp.edu.repository.ProdutoRepository;
import ifsp.edu.sqlitedao.CompraProdutoDAOImpl;
import ifsp.edu.sqlitedao.ProdutoDAOImpl;
import ifsp.edu.usecases.cliente.FindClienteUseCase;
import ifsp.edu.usecases.compraproduto.CompraProdutoDAO;
import ifsp.edu.usecases.compraproduto.InsertCompraProdutoUseCase;
import ifsp.edu.usecases.compraproduto.RemoveCompraProdutoUseCase;
import ifsp.edu.usecases.produto.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CtrlSubmenuCompraProduto {
    @FXML
    ChoiceBox<String> cbProdutos;
    @FXML
    TextField txtValorProduto;

    @FXML
    TableView<CompraProduto> tableCompra;
    @FXML
    TableColumn<CompraProduto, Produto> colProduto;
    @FXML
    TableColumn<CompraProduto, LocalDate> colData;
    @FXML
    TableColumn<CompraProduto, Double> colValor;
    @FXML
    Button btnComprar;
    @FXML
    Button btnRemover;

    ObservableList<CompraProduto> compras;
    InsertCompraProdutoUseCase insertCompraProdutoUseCase;
    RemoveCompraProdutoUseCase removeCompraProdutoUseCase;

    ProdutoDAO daoProdutos = new ProdutoDAOImpl();



    public void initialize(){
        colProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colData.setCellValueFactory(new PropertyValueFactory<>("momento"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        addDataInCb();
        compras = FXCollections.observableArrayList();
        loadTable();
        tableCompra.setItems(compras);


    }

    private Produto getProdutoFromView(){
        String nomeProduto = String.valueOf(cbProdutos.getSelectionModel().getSelectedItem());
        Produto produto = daoProdutos.findByNome(nomeProduto);
        Double valor = Double.valueOf(txtValorProduto.getText());
        if(valor != null && valor > 0.0){
            produto.setValorCusto(valor);
        }


        return produto;
    }




    public void addDataInCb(){
        List<Produto> produtosArrayList = new ArrayList<>(daoProdutos.listAll());                     //retorna
        ObservableList<String> produtos = FXCollections.observableArrayList(produtosArrayList.stream().map(c -> c.getNome()).collect(Collectors.toList()));
        ObservableList<String> produtosCadastrados = FXCollections.observableArrayList(produtos);
        cbProdutos.setItems(produtosCadastrados);
    }


    public void comprarProduto(ActionEvent actionEvent) {
        Produto prod = getProdutoFromView();
        if(prod != null){
            save(prod);
            reloadTable();
        }
    }

    private void reloadTable() {
        compras.clear();
        loadTable();
        System.out.println(compras);
        tableCompra.setItems(compras);

    }

    private void loadTable() {
        CompraProdutoDAO dao = new CompraProdutoDAOImpl();
        List<CompraProduto> comps = new ArrayList<>(dao.listAll());
        compras.setAll(comps);
    }

    private void save(Produto prod) {
        CompraProduto comp = new CompraProduto(prod,LocalDate.now());
        CompraProdutoDAO dao = new CompraProdutoDAOImpl();
        insertCompraProdutoUseCase = new InsertCompraProdutoUseCase(dao);
        insertCompraProdutoUseCase.insert(comp);


    }


    public void removerCompra(ActionEvent actionEvent) {
        CompraProduto comp = tableCompra.getSelectionModel().getSelectedItem();
        CompraProdutoDAO dao = new CompraProdutoDAOImpl();;
        removeCompraProdutoUseCase = new RemoveCompraProdutoUseCase(dao);
        removeCompraProdutoUseCase.delete(comp);
        reloadTable();

    }
}
