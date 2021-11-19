package ifsp.edu.controller.produtos;

import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;
import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.repository.ProdutoRepository;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;
import ifsp.edu.usecases.fornecedor.InsertFornecedorUseCase;
import ifsp.edu.usecases.produto.InserirProdutoUseCase;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.view.produtos.WindowSubmenuProdutos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CtrlCadatroProdutos {
    @FXML
    TextField txtIdProduto;
    @FXML
    TextField txtNomeProduto;
    @FXML
    TextField txtDescricaoProduto;
    @FXML
    TextField txtPrecoCustoProduto;
    @FXML
    TextField txtPrecoVendaProduto;
    @FXML
    ChoiceBox cbFornecedoresProdutos;
    @FXML
    Button btnAdicionarFornecedor;

    @FXML
    Button btnCadastrarProduto;

    List<Fornecedor> fornecedoresProduto = new ArrayList<>();

    private InserirProdutoUseCase inserirProdutoUseCase;

    public void initialize() {
        popularCbFornecedores();
    }

    private void popularCbFornecedores() {
        FornecedorDAO dao = new FornecedorRepository();
//        List<Fornecedor> fornecedores = dao.listAll();
        List<Fornecedor> fornecedores = dao.listAll();
        List<String> newArray = fornecedores.stream().map(f -> f.getNome()).collect(Collectors.toList());
        System.out.println(fornecedores);

        ObservableList<String> data = FXCollections.observableArrayList(newArray);
        cbFornecedoresProdutos.getItems().addAll(newArray);
    }

    public void cadastrarProduto(ActionEvent actionEvent) {
        saveOrUpdate();
        close();
    }

    private void close() {
        Stage stage = (Stage) txtIdProduto.getScene().getWindow();
        stage.close();
    }

    public void btnVoltar(ActionEvent actionEvent) {
        WindowSubmenuProdutos window = new WindowSubmenuProdutos();
        try {
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarFornecedor() {


    }

    public void saveOrUpdate() throws RuntimeException{
        Produto p = getFromProdutoToView();
//        if (p == null && p != null ) {
//            save(p);
//        } else if (p != null && p != null ) {
////            update(getFromProdutoToView());
//        }
        save(p);
        Stage stage = (Stage) txtIdProduto.getScene().getWindow();

    }

    private void save(Produto p) {
        ProdutoDAO dao = new ProdutoRepository();
        inserirProdutoUseCase = new InserirProdutoUseCase(dao);
        inserirProdutoUseCase.insert(p);
    }


    private void delete(){

    }


    private Produto getFromProdutoToView() {

        Integer id = Integer.valueOf(txtIdProduto.getText());
        String nome = String.valueOf(txtNomeProduto.getText());
        String descricao = String.valueOf(txtDescricaoProduto.getText());
        Double precoCusto = Double.valueOf(txtPrecoCustoProduto.getText());
        Double precoVenda = Double.valueOf(txtPrecoVendaProduto.getText());
        String nomeFornecedor = (String) cbFornecedoresProdutos.getValue();
        FornecedorDAO dao = new FornecedorRepository();
        Fornecedor f = dao.findByName(nomeFornecedor);
        Produto produto = new Produto( nome,id, descricao, precoCusto, precoVenda, f);
        return produto;
    }
//
}
