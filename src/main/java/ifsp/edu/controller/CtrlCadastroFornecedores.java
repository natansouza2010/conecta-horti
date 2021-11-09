package ifsp.edu.controller;

import ifsp.edu.view.WindowCadastroFornecedores;
import ifsp.edu.view.WindowSubmenuFornecedores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CtrlCadastroFornecedores {

    @FXML TextField txtCnpjFornecedor;
    @FXML TextField txtNomeFornecedor;
    @FXML TextField txtTel1Fornecedor;
    @FXML TextField txtTel2Fornecedor;
    @FXML TextField txtEnderecoFornecedor;
    @FXML Button btnCadastrarFornecedor;

    public void cadastrarFornecedor(ActionEvent actionEvent) {
        WindowSubmenuFornecedores window = new WindowSubmenuFornecedores();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void btnVoltar(ActionEvent actionEvent) {
        WindowSubmenuFornecedores window = new WindowSubmenuFornecedores();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}
