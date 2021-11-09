
package ifsp.edu.controller;

import ifsp.edu.view.WindowSubmenuClientes;
import ifsp.edu.view.WindowSubmenuFornecedores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class CtlrMenuPrincipal {

    @FXML Button btnGerenciarClientes;
    @FXML Button btnGerenciarProdutos;
    @FXML Button btnGerenciarFornecedores;
    @FXML Button btnGerenciarCatalogo;
    @FXML Button btnRenda;
    @FXML Button btnGerenciarPedido;
    
    public void submenuClientes(ActionEvent actionEvent) throws IOException {
        WindowSubmenuClientes window = new WindowSubmenuClientes();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void submenuProdutos(ActionEvent actionEvent) {
    }

    public void submenuFornecedores(ActionEvent actionEvent) {
        WindowSubmenuFornecedores window = new WindowSubmenuFornecedores();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void submenuRenda(ActionEvent actionEvent) {
    }

    public void submenuPedidos(ActionEvent actionEvent) {
    }

    public void submenuCatalogo(ActionEvent actionEvent) {
    }
}

