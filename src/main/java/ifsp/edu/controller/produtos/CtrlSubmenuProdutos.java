package ifsp.edu.controller.produtos;

import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;
import ifsp.edu.usecases.produto.ProdutoDAO2;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;
import ifsp.edu.view.principal.WindowPrincipal;
import ifsp.edu.view.produtos.WindowCadastroProdutos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class CtrlSubmenuProdutos {

    private static ProdutoDAO2 dao;

    @FXML Button btnAdicionarProdutos;
    @FXML Button btnRemoverProdutos;
    @FXML Button btnEditarProdutos;
    @FXML Button btnBuscarProdutos;
    @FXML TextField txtBuscarProdutos;

    @FXML TableColumn colIDProdutos;
    @FXML TableColumn colNomeProdutos;
    @FXML TableColumn colDescricaoProdutos;
    @FXML TableColumn colPrecoCustoProdutos;
    @FXML TableColumn colPrecoVendaProdutos;
    @FXML TableColumn colFornecedoresProduto;


    public void adicionarProdutos(ActionEvent actionEvent) {
        WindowCadastroProdutos window = new WindowCadastroProdutos();
        try {
            window.show();
            Produto produto = getProdutoFromView();

//            dao.insert(produto);
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void removerProdutos(ActionEvent actionEvent) {
        Produto produto = null;
        dao.insert(produto);
    }

    public void editarProdutos(ActionEvent actionEvent) {
        WindowCadastroProdutos window = new WindowCadastroProdutos();
        try {
            window.show();
            Produto produto = null;
            dao.update(produto);
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void buscarProdutos(ActionEvent actionEvent) {
        List<Produto> produtos = null;
        produtos = dao.listAll();
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
        String cnpjFornecedor = String.valueOf(colFornecedoresProduto.getText());
        FornecedorRepository fornecedorDAO = new FornecedorRepository();
        //Fornecedor fornecedor = fornecedorDAO.findOne(cnpjFornecedor);
        Fornecedor fornecedor = null;
        Produto produto = new Produto(nomeProduto,idProduto,descProduto,precoCustoProduto,precoVendaProduto,fornecedor);
        return produto;

    }
}
