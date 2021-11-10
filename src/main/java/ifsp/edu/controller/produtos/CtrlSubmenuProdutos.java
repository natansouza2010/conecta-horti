package ifsp.edu.controller.produtos;

import ifsp.edu.dao.ProdutoDAO;
import ifsp.edu.model.Produto;
import ifsp.edu.view.fornecedores.WindowCadastroFornecedores;
import ifsp.edu.view.principal.WindowPrincipal;
import ifsp.edu.view.produtos.WindowCadastroProdutos;
import ifsp.edu.view.produtos.WindowSubmenuProdutos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class CtrlSubmenuProdutos {

    private static ProdutoDAO dao;

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
            Produto produto = null;
            window.show();
            dao.adicionarProduto(produto);
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void removerProdutos(ActionEvent actionEvent) {
        dao.removerProduto(1);
    }

    public void editarProdutos(ActionEvent actionEvent) {
        WindowCadastroProdutos window = new WindowCadastroProdutos();
        try {
            window.show();
            dao.editarProduto(null,null,null,null,null,0);
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void buscarProdutos(ActionEvent actionEvent) {
        List<Produto> produtos = null;
        produtos = dao.buscarProdutos();
    }

    public void voltarParaMenu(ActionEvent actionEvent) {
        WindowPrincipal window = new WindowPrincipal();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}
