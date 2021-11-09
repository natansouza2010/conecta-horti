package ifsp.edu.controller.produtos;

import ifsp.edu.view.produtos.WindowSubmenuProdutos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CtrlCadatroProdutos {

    @FXML TextField txtNomeProduto;
    @FXML TextField txtDescricaoProduto;
    @FXML TextField txtPrecoCustoProduto;
    @FXML TextField txtPrecoVendaProduto;
    @FXML ChoiceBox cbFornecedoresProdutos;

    public void cadastrarProduto(ActionEvent actionEvent) {
        WindowSubmenuProdutos window = new WindowSubmenuProdutos();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void btnVoltar(ActionEvent actionEvent) {
        WindowSubmenuProdutos window = new WindowSubmenuProdutos();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}
