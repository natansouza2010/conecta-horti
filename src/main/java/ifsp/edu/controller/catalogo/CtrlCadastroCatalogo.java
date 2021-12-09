package ifsp.edu.controller.catalogo;

import ifsp.edu.model.Catalogo;
import ifsp.edu.model.ItemCatalogo;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;
import ifsp.edu.repository.CatalogoRepository;
import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.repository.ProdutoRepository;
import ifsp.edu.sqlitedao.CatalogoDAOImpl;
import ifsp.edu.sqlitedao.ItemCatalogoDAOImpl;
import ifsp.edu.sqlitedao.ProdutoDAOImpl;
import ifsp.edu.usecases.catalogo.CatalogoDAO;
import ifsp.edu.usecases.catalogo.InserirCatalogoUseCase;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;
import ifsp.edu.usecases.produto.InserirProdutoUseCase;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.view.catalogo.WindowSubmenuCatalogo;
import ifsp.edu.view.produtos.WindowSubmenuProdutos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CtrlCadastroCatalogo {

    @FXML
    TableView<ItemCatalogo> table;
    @FXML
    TableColumn<ItemCatalogo, Produto> colProduto;

    @FXML
    TableColumn<ItemCatalogo, Double> colValorVenda;


    @FXML
    TextField txtValorVenda;

    @FXML
    Button btnCadastrarCatalogo;

    @FXML
    Button btnAdicionarProdutoCatalogo;

    @FXML
    ChoiceBox cbProdutosDisponiveis;

    ProdutoDAO daoProdutos = new ProdutoDAOImpl();

    ObservableList<ItemCatalogo> produtosDoCat;
    List<ItemCatalogo> produtosDoCatalogo = new ArrayList<>();


    private Catalogo catalogo;
    private InserirCatalogoUseCase inserirCatalogoUseCase;


    public void initialize() {
        colProduto.setCellValueFactory(new PropertyValueFactory<ItemCatalogo,Produto>("produto"));
        colValorVenda.setCellValueFactory(new PropertyValueFactory<ItemCatalogo,Double>("valor"));
        popularCbProdutos();

    }

    private void popularCbProdutos() { ;

        List<Produto> produtosArrayList = new ArrayList<>(daoProdutos.listAll());                     //retorna
        ObservableList<String> produtos = FXCollections.observableArrayList(produtosArrayList.stream().map(c -> c.getNome()).collect(Collectors.toList()));
        ObservableList<String> produtosCadastrados = FXCollections.observableArrayList(produtos);
        cbProdutosDisponiveis.setItems(produtosCadastrados);
        produtosDoCat = FXCollections.observableArrayList();
        table.setItems(produtosDoCat);
    }

    public void cadastrarCatalogo(ActionEvent actionEvent) {
//        saveOrUpdate();
//        close();
    }

    public void saveOrUpdate() throws RuntimeException {
        Catalogo c = getFromCatalogoToView();
        System.out.println(c.toString());
        if (c!= null) {
            save(c);
        }

        Stage stage = (Stage) txtValorVenda.getScene().getWindow();

    }

    private void save(Catalogo c) {
        CatalogoDAO catalogoDAO = new CatalogoDAOImpl();
        inserirCatalogoUseCase = new InserirCatalogoUseCase(catalogoDAO);
        inserirCatalogoUseCase.insert(c);
    }



    private Catalogo getFromCatalogoToView() {
        LocalDate data = LocalDate.now();
        Catalogo catalogo = new Catalogo();
        catalogo.setDataInicial(data);
        catalogo.setDataFinal(data.plusDays(7));
        List<Produto> listAux = new ArrayList<>();
        produtosDoCatalogo.stream().forEach(i -> listAux.add(i.getProduto()));
        catalogo.setProdutos(listAux);

        return catalogo;
    }



    public void voltar(ActionEvent actionEvent) {
        close();
  }

    public void close(){
         Stage stage = (Stage) btnCadastrarCatalogo.getScene().getWindow();
         stage.close();
   }

    public void adicionarProdutoToTable(ActionEvent actionEvent) {
        String nomeProduto = String.valueOf(cbProdutosDisponiveis.getSelectionModel().getSelectedItem());
        Produto produto = daoProdutos.findByNome(nomeProduto);
        ItemCatalogoDAOImpl itemCatalogoDAO = new ItemCatalogoDAOImpl();
        ItemCatalogo itemCatalogo = new ItemCatalogo();
        itemCatalogo.setCatalogo(catalogo);
        itemCatalogo.setProduto(produto);
        itemCatalogo.setValor(Double.valueOf(txtValorVenda.getText()));
        produtosDoCatalogo.add(itemCatalogo);
        produtosDoCat.setAll(produtosDoCatalogo);
        itemCatalogoDAO.insert(itemCatalogo);
        table.setItems(produtosDoCat);


    }
}
