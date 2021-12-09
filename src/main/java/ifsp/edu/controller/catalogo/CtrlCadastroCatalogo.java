package ifsp.edu.controller.catalogo;

import ifsp.edu.model.Catalogo;
import ifsp.edu.model.ItemCatalogo;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;
import ifsp.edu.repository.CatalogoRepository;
import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.repository.ProdutoRepository;
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
//    List<Produto> produtosDisponiveis = new ArrayList<>();
//    private Integer idCatalogo = 1;
//
//    private Catalogo catalogo;
//    private InserirCatalogoUseCase inserirCatalogoUseCase;ENDERECO


    public void initialize() {
        colProduto.setCellValueFactory(new PropertyValueFactory<ItemCatalogo,Produto>("produto"));
        colValorVenda.setCellValueFactory(new PropertyValueFactory<ItemCatalogo,Double>("valor"));
        popularCbProdutos();
        table.
    }

    private void popularCbProdutos() {
//        ProdutoDAO dao = new ProdutoRepository();
//        List<Produto> produtos = dao.listAll();
//        List<String> newArray = produtos.stream().map(p -> p.getNome()).collect(Collectors.toList());
//        System.out.println(produtos);
//
//        ObservableList<String> data = FXCollections.observableArrayList(newArray);
//        cbProdutosDisponiveis.getItems().addAll(newArray);

        List<Produto> produtosArrayList = new ArrayList<>(daoProdutos.listAll());                     //retorna
        ObservableList<String> produtos = FXCollections.observableArrayList(produtosArrayList.stream().map(c -> c.getNome()).collect(Collectors.toList()));
        ObservableList<String> produtosCadastrados = FXCollections.observableArrayList(produtos);
        cbProdutosDisponiveis.setItems(produtosCadastrados);
    }

    public void cadastrarCatalogo(ActionEvent actionEvent) {
//        saveOrUpdate();
//        close();
    }

    public void saveOrUpdate() throws RuntimeException {
        Catalogo c = getFromCatalogoToView();
        if (c == null && c != null) {
            save(c);
        }

        Stage stage = (Stage) txtValorVenda.getScene().getWindow();

    }

    private void save(Catalogo c) {
//        CatalogoDAO dao = new CatalogoRepository();
//        inserirCatalogoUseCase = new InserirCatalogoUseCase(dao);
//        inserirCatalogoUseCase.insert(c);
    }

    public void setCatalogoToView(Catalogo c) {
//        catalogo=c;
//        txtIdProduto.setText(c.getId().toString());
//        txtNomeProduto.setText(c.getProduto().getNome());
//        txtDataInicial.setText(c.getDataInicial().toString());
//        txtDataFinal.setText(c.getDataFinal().toString());
    }

    private Catalogo getFromCatalogoToView() {
        Catalogo catalogo = null;
//        Integer id = Integer.valueOf(txtIdProduto.getText());
//        String nome = String.valueOf(txtNomeProduto.getText());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        CharSequence data1 = String.valueOf(txtDataInicial.getText());
//        CharSequence data2 = String.valueOf(txtDataFinal.getText());
//        LocalDate dataInicial = LocalDate.parse(data1,formatter);
////        LocalDate dataFinal = LocalDate.parse(data2,formatter);
//        CatalogoDAO dao = new CatalogoRepository();
//        Produto produto = dao.findProdutoByName(nome).get();
//        Catalogo catalogo = new Catalogo( idCatalogo,dataInicial,dataFinal,produto);
//        idCatalogo++;
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
    }
}
