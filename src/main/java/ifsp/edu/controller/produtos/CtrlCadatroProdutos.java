package ifsp.edu.controller.produtos;

import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;
import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.repository.ProdutoRepository;
import ifsp.edu.sqlitedao.FornecedorDAOImpl;
import ifsp.edu.sqlitedao.ProdutoDAOImpl;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;
import ifsp.edu.usecases.fornecedor.InsertFornecedorUseCase;
import ifsp.edu.usecases.fornecedor.UpdateFornecedorUseCase;
import ifsp.edu.usecases.produto.InserirProdutoUseCase;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.usecases.produto.UpdateProdutoUseCase;
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

    @FXML TextField txtIdProduto;
    @FXML TextField txtNomeProduto;
    @FXML TextField txtDescricaoProduto;
    @FXML TextField txtPrecoCustoProduto;
    @FXML TextField txtPrecoVendaProduto;
    @FXML ChoiceBox cbFornecedoresProdutos;
    @FXML Button btnAdicionarFornecedor;
    @FXML Button btnCadastrarProduto;

    private FornecedorDAO fornecedorDAO = new FornecedorDAOImpl();
    private ProdutoDAO produtoDAO = new ProdutoDAOImpl();

    public void initialize() {
        popularCbFornecedores();
    }

    private void popularCbFornecedores() {
        List<Fornecedor> fornecedores = fornecedorDAO.listAll();
        List<String> newArray = fornecedores.stream().map(f -> f.getNome()).collect(Collectors.toList());

        ObservableList<String> data = FXCollections.observableArrayList(newArray);
        cbFornecedoresProdutos.getItems().addAll(newArray);
    }

    Produto produto;
    public void setProdutoToView(Produto p) {
        produto=p;
        txtIdProduto.setText(p.getId().toString());
        txtNomeProduto.setText(p.getNome());
        txtDescricaoProduto.setText(p.getDescricao());
        txtPrecoCustoProduto.setText(p.getValorCusto().toString());
        txtPrecoVendaProduto.setText(p.getValorVenda().toString());
    }

    public void cadastrarProduto(ActionEvent actionEvent) {
        saveOrUpdate();
        close();
    }

    public void adicionarFornecedor(ActionEvent actionEvent) {
        InserirProdutoUseCase inserirProdutoUseCase;
    }

    public void saveOrUpdate() throws RuntimeException{
        Produto p = getFromProdutoToView();
        if (p == null && p != null ) {
            save(p);
        } else if (p != null && p != null ) {
            update(getFromProdutoToView());
        }
        save(p);
        Stage stage = (Stage) txtIdProduto.getScene().getWindow();

    }
    private Produto getFromProdutoToView() {
        Integer id = Integer.valueOf(txtIdProduto.getText());
        String nome = String.valueOf(txtNomeProduto.getText());
        String descricao = String.valueOf(txtDescricaoProduto.getText());
        Double precoCusto = Double.valueOf(txtPrecoCustoProduto.getText());
        Double precoVenda = Double.valueOf(txtPrecoVendaProduto.getText());
        String nomeFornecedor = (String) cbFornecedoresProdutos.getValue();
        Fornecedor f = fornecedorDAO.findByName(nomeFornecedor);
        Produto produto = new Produto( nome,id, descricao, precoCusto, precoVenda, f);
        return produto;
    }

    private void update(Produto p) {
        UpdateProdutoUseCase updateProdutoUseCase = new UpdateProdutoUseCase(produtoDAO);
        updateProdutoUseCase.update(p.getId());
    }

    private void save(Produto p) {
        InserirProdutoUseCase inserirProdutoUseCase = new InserirProdutoUseCase(produtoDAO);
        inserirProdutoUseCase.insert(p);
    }

    public void voltar(ActionEvent actionEvent) {
        close();
    }

    private void close() {
        Stage stage = (Stage) txtIdProduto.getScene().getWindow();
        stage.close();
    }
}
