package ifsp.edu.controller;

import ifsp.edu.view.WindowCadastroClientes;
import ifsp.edu.view.WindowPrincipal;
import ifsp.edu.view.WindowSubmenuClientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CtlrSubmenuClientes {

    @FXML Button btnAdicionarCliente;
    @FXML Button btnRemoverCliente;
    @FXML Button btnEditarCliente;
    @FXML Button btnBuscarCliente;
    @FXML TextField txtBuscarCliente;

    @FXML TableColumn colCpfCliente;
    @FXML TableColumn colNomeCliente;
    @FXML TableColumn colTel1Cliente;
    @FXML TableColumn colTel2Cliente;
    @FXML TableColumn colEnderecoCliente;

    public void adicionarCliente(ActionEvent actionEvent) throws IOException {
        WindowCadastroClientes window = new WindowCadastroClientes();
        try {
            window.showAndWait();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void removerCliente(ActionEvent actionEvent) {
    }

    public void editarCliente(ActionEvent actionEvent) {
    }

    public void buscarCliente(ActionEvent actionEvent) {
    }

    public void voltarParaMenu(ActionEvent actionEvent) {

    }
}
