package ifsp.edu.controller.produtos;

import ifsp.edu.repository.ClienteRepository;
import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.repository.ProdutoRepository;
import ifsp.edu.sqlitedao.FornecedorDAOImpl;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.usecases.cliente.InserirClienteUseCase;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;
import ifsp.edu.usecases.produto.*;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private DeleteProdutoUseCase deleteProdutoUseCase;
    private FindProdutoUseCase findProdutoUseCase;
    private InserirProdutoUseCase inserirProdutoUseCase;

    public void initialize(){
        colIDProdutos.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("id"));
        colNomeProdutos.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
        colDescricaoProdutos.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
        colPrecoCustoProdutos.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valorCusto"));
        colPrecoVendaProdutos.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valorVenda"));
        colFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, Fornecedor>("fornecedor"));
        FornecedorDAO daoForn = new FornecedorDAOImpl();
        List<Fornecedor> fornecedors = daoForn.listAll();
        System.out.println(fornecedors.toString());
        ProdutoDAO dao = new ProdutoRepository();
        inserirProdutoUseCase = new InserirProdutoUseCase(dao);
        Produto p1 = new Produto("banana",1,"1kg",3.00,5.00,fornecedors.get(1));
        Produto p2 = new Produto("tomate",2,"1kg",5.00,8.00, fornecedors.get(2));
        Produto p3 = new Produto("vagem",3,"1pct",1.50,7.00, fornecedors.get(1));
        inserirProdutoUseCase.insert(p1);
        inserirProdutoUseCase.insert(p2);
        inserirProdutoUseCase.insert(p3);
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
        Produto produto = tableProduto.getSelectionModel().getSelectedItem();
        ProdutoDAO dao = new ProdutoRepository();
        deleteProdutoUseCase = new DeleteProdutoUseCase(dao);
        deleteProdutoUseCase.delete(produto);
        reloadTable();
    }

    public void editarProdutos(ActionEvent actionEvent) {
       /* WindowCadastroProdutos window = new WindowCadastroProdutos();
        try {
            window.show();
            Produto produto = null;
//            dao.update(produto);
        } catch (IOException e ) {
            e.printStackTrace();
        }*/
    }

    public void buscarProdutos(ActionEvent actionEvent) {
        Integer idProduto = Integer.valueOf(txtBuscarProdutos.getText());
        ProdutoDAO dao = new ProdutoRepository();
        findProdutoUseCase = new FindProdutoUseCase(dao);
        Optional<Produto> p = findProdutoUseCase.findOne(idProduto);

        produtos.clear();
        produtos.add(p.get());
        tableProduto.setItems(produtos);

    }

    public void voltar(ActionEvent actionEvent) {
        close();
    }

    public void close(){
        Stage stage = (Stage) btnAdicionarProdutos.getScene().getWindow();
        stage.close();
    }
}
