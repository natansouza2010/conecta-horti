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
import ifsp.edu.usecases.produto.ProdutoValidator;
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
        txtIdProduto.setDisable(true);
        txtNomeProduto.setText(p.getNome());
        txtDescricaoProduto.setText(p.getDescricao());
        txtPrecoCustoProduto.setText(p.getValorCusto().toString());
        txtPrecoVendaProduto.setText(p.getValorVenda().toString());
        cbFornecedoresProdutos.setValue(p.getFornecedor().getNome());
        cbFornecedoresProdutos.setDisable(true);
    }

    public void cadastrarProduto(ActionEvent actionEvent) {
        saveOrUpdate();
        close();
    }



    public void saveOrUpdate() throws RuntimeException{
        Produto p = getFromProdutoToView();
        if (produto == null && p != null ) {
            save(p);
        } else if (produto != null && p != null ) {
            update(p);
        }
        Stage stage = (Stage) txtIdProduto.getScene().getWindow();

    }

    public String validate(String textfield){
        if(textfield.isEmpty()){
            throw new IllegalArgumentException("Campo vazio!");
        }
        return textfield;


    }
    private Produto getFromProdutoToView() {


        Integer id = Integer.valueOf(validate(txtIdProduto.getText()));
        String nome = String.valueOf(validate(txtNomeProduto.getText()));
        String descricao = String.valueOf(validate(txtDescricaoProduto.getText()));
        Double precoCusto = Double.valueOf(validate(txtPrecoCustoProduto.getText()));
        Double precoVenda = Double.valueOf(validate(txtPrecoVendaProduto.getText()));
        String nomeFornecedor = validate((String) cbFornecedoresProdutos.getValue());

        System.out.println(id);
        System.out.println(nome);
        System.out.println(descricao);
        System.out.println(precoCusto);
        System.out.println(precoVenda);
        System.out.println(nomeFornecedor);

        Fornecedor f = fornecedorDAO.findByName(nomeFornecedor);
        Produto produto = new Produto( id, nome, descricao, precoCusto, precoVenda, f);
        return produto;
    }

    private void update(Produto p) {
        UpdateProdutoUseCase updateProdutoUseCase = new UpdateProdutoUseCase(produtoDAO);
        updateProdutoUseCase.update(p);
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
