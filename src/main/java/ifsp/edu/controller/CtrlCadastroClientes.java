package ifsp.edu.controller;

import ifsp.edu.view.WindowCadastroClientes;
import ifsp.edu.view.WindowSubmenuClientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CtrlCadastroClientes {

    @FXML TextField txtCpfCliente;
    @FXML TextField txtNomeCliente;
    @FXML TextField txtTel1Cliente;
    @FXML TextField txtTel2Cliente;
    @FXML TextField txtEnderecoCliente;
    @FXML Button btnCadastrarCliente;

    public void btnVoltar(ActionEvent actionEvent) {
        WindowSubmenuClientes window = new WindowSubmenuClientes();
        try {
            window.show();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void cadastrarCliente(ActionEvent actionEvent) {
        WindowSubmenuClientes window = new WindowSubmenuClientes();
        try {
            window.show();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }
}
