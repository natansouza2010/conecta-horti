package ifsp.edu.controller.compraproduto;

import ifsp.edu.model.Cliente;
import ifsp.edu.model.CompraProduto;
import ifsp.edu.model.Produto;
import ifsp.edu.repository.ProdutoRepository;
import ifsp.edu.usecases.produto.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    ProdutoDAO daoProdutos = new ProdutoRepository();


    public void initialize(){

    }


    public void addDataInCb(){
        List<Produto> produtosArrayList = new ArrayList<>(daoProdutos.listAll());                     //retorna
        ObservableList<String> produtos = FXCollections.observableArrayList(produtosArrayList.stream().map(c -> c.getNome()).collect(Collectors.toList()));
        ObservableList<String> produtosCadastrados = FXCollections.observableArrayList(produtos);
        cbProdutos.setItems(produtosCadastrados);
    }



}
