package ifsp.edu.controller.produtos;

import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.repository.ProdutoRepository;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.usecases.produto.ProdutoDAO2;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;
import ifsp.edu.view.principal.WindowPrincipal;
import ifsp.edu.view.produtos.WindowCadastroProdutos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CtrlSubmenuProdutos {
    @FXML Button btnAdicionarProdutos;
    @FXML Button btnRemoverProdutos;
    @FXML Button btnEditarProdutos;
    @FXML Button btnBuscarProdutos;
    @FXML TextField txtBuscarProdutos;

    @FXML
    TableView<Produto> tableProduto;

    ObservableList<Produto> produtos;

    @FXML TableColumn<Produto,Integer> colIDProdutos;
    @FXML TableColumn<Produto, String>colNomeProdutos;
    @FXML TableColumn<Produto, String> colDescricaoProdutos;
    @FXML TableColumn<Produto, Double> colPrecoCustoProdutos;
    @FXML TableColumn<Produto, Double> colPrecoVendaProdutos;
    @FXML TableColumn<Fornecedor, Fornecedor> colFornecedor;


    public void initialize(){
        colIDProdutos.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("id"));
        colNomeProdutos.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
        colDescricaoProdutos.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
        colPrecoCustoProdutos.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valorCusto"));
        colPrecoVendaProdutos.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valorVenda"));
        colFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, Fornecedor>("fornecedor"));
        produtos = FXCollections.observableArrayList();
        loadTable();
        tableProduto.setItems(produtos);

    }

    private void loadTable() {
        ProdutoDAO dao = new ProdutoRepository();
        List<Produto> prod = new ArrayList<>(dao.listAll());
        produtos = FXCollections.observableArrayList(prod);

    }

    private void reloadTable(){
        produtos.clear();
        loadTable();
        tableProduto.setItems(produtos);
    }


    public void adicionarProdutos(ActionEvent actionEvent) {
        WindowCadastroProdutos window = new WindowCadastroProdutos();
        try {
            window.show();
            reloadTable();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void removerProdutos(ActionEvent actionEvent) {
        Produto produto = null;
//        dao.insert(produto);
    }

    public void editarProdutos(ActionEvent actionEvent) {
        WindowCadastroProdutos window = new WindowCadastroProdutos();
        try {
            window.show();
            Produto produto = null;
//            dao.update(produto);
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void buscarProdutos(ActionEvent actionEvent) {
//        List<Produto> produtos = null;
//        produtos = dao.listAll();
    }

    public void voltarParaMenu(ActionEvent actionEvent) {
        WindowPrincipal window = new WindowPrincipal();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }


    private Produto getProdutoFromView(){
        Integer idProduto = Integer.valueOf(colIDProdutos.getText());
        String nomeProduto = String.valueOf(colNomeProdutos.getText());
        String descProduto = String.valueOf(colDescricaoProdutos.getText());
        Double precoCustoProduto  = Double.valueOf(colPrecoCustoProdutos.getText());
        Double precoVendaProduto = Double.valueOf(colPrecoVendaProdutos.getText());
        Fornecedor fornecedor = (Fornecedor) colFornecedor.getCellValueFactory();
//        FornecedorDAO fornecedorDAO = new FornecedorRepository();
//        //Fornecedor fornecedor = fornecedorDAO.findOne(cnpjFornecedor);
        Produto produto = new Produto(nomeProduto,idProduto,descProduto,precoCustoProduto,precoVendaProduto,fornecedor);
        return produto;

    }
}
