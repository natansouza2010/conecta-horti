package ifsp.edu.controller;

import ifsp.edu.view.WindowCadastroFornecedores;
import ifsp.edu.view.WindowPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CtlrSubmenuFornecedores {

    @FXML
    Button btnAdicionarFornecedor;
    @FXML Button btnRemoverFornecedor;
    @FXML Button btnEditarFornecedor;
    @FXML Button btnBuscarFornecedor;
    @FXML
    TextField txtBuscarFornecedor;

    @FXML
    TableColumn colCnpjFornecedor;
    @FXML TableColumn colNomeFornecedor;
    @FXML TableColumn colTel1Fornecedor;
    @FXML TableColumn colTel2Fornecedor;
    @FXML TableColumn colEnderecoFornecedor;
    @FXML TableColumn colRazaoFornecedor;

    public void adicionarFornecedor(ActionEvent actionEvent) {
        WindowCadastroFornecedores window = new WindowCadastroFornecedores();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void removerFornecedor(ActionEvent actionEvent) {

    }

    public void editarFornecedor(ActionEvent actionEvent) {
        WindowCadastroFornecedores window = new WindowCadastroFornecedores();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void buscarFornecedor(ActionEvent actionEvent) {
    }

    public void voltarParaMenu(ActionEvent actionEvent) {
        WindowPrincipal window = new WindowPrincipal();
        try {
            window.show();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }
}
