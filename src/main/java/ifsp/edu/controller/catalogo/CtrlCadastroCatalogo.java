package ifsp.edu.controller.catalogo;

import ifsp.edu.model.Catalogo;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;
import ifsp.edu.repository.CatalogoRepository;
import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.repository.ProdutoRepository;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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
    TextField txtIdProduto;
    @FXML
    TextField txtNomeProduto;
    @FXML
    TextField txtDataInicial;
    @FXML
    TextField txtDataFinal;

    @FXML
    Button btnCadastrarCatalogo;

    @FXML
    ChoiceBox cbProdutosDisponiveis;

    List<Produto> produtosDisponiveis = new ArrayList<>();
    private Integer idCatalogo = 1;

    private Catalogo catalogo;
    private InserirCatalogoUseCase inserirCatalogoUseCase;


    public void initialize() {
        popularCbProdutos();
    }

    private void popularCbProdutos() {
        ProdutoDAO dao = new ProdutoRepository();
        List<Produto> produtos = dao.listAll();
        List<String> newArray = produtos.stream().map(p -> p.getNome()).collect(Collectors.toList());
        System.out.println(produtos);

        ObservableList<String> data = FXCollections.observableArrayList(newArray);
        cbProdutosDisponiveis.getItems().addAll(newArray);
    }

    public void cadastrarCatalogo(ActionEvent actionEvent) {
        saveOrUpdate();
        close();
    }

    private void close() {
        Stage stage = (Stage) txtIdProduto.getScene().getWindow();
        stage.close();
    }

    public void btnVoltar(ActionEvent actionEvent) {
        WindowSubmenuCatalogo window = new WindowSubmenuCatalogo();
        try {
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveOrUpdate() throws RuntimeException{
        Catalogo c = getFromCatalogoToView();
//        if (p == null && p != null ) {
//            save(p);
//        } else if (p != null && p != null ) {
////            update(getFromProdutoToView());
//        }
        save(c);
        Stage stage = (Stage) txtIdProduto.getScene().getWindow();

    }

    private void save(Catalogo c) {
        CatalogoDAO dao = new CatalogoRepository();
        inserirCatalogoUseCase = new InserirCatalogoUseCase(dao);
        inserirCatalogoUseCase.insert(c);
    }

    public void setCatalogoToView(Catalogo c) {
        catalogo=c;
        txtIdProduto.setText(c.getId().toString());
        txtNomeProduto.setText(c.getProduto().getNome());
        txtDataInicial.setText(c.getDataInicial().toString());
        txtDataFinal.setText(c.getDataFinal().toString());
    }

    private Catalogo getFromCatalogoToView() {

        Integer id = Integer.valueOf(txtIdProduto.getText());
        String nome = String.valueOf(txtNomeProduto.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        CharSequence data1 = String.valueOf(txtDataInicial.getText());
        CharSequence data2 = String.valueOf(txtDataFinal.getText());
        LocalDate dataInicial = LocalDate.parse(data1,formatter);
        LocalDate dataFinal = LocalDate.parse(data2,formatter);
        CatalogoDAO dao = new CatalogoRepository();
        Produto produto = dao.findProdutoByName(nome).get();
        Catalogo catalogo = new Catalogo( idCatalogo,dataInicial,dataFinal,produto);
        idCatalogo++;
        return catalogo;
    }
}
