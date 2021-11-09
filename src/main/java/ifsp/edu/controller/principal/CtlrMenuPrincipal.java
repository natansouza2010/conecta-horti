
package ifsp.edu.controller.principal;

import ifsp.edu.view.clientes.WindowSubmenuClientes;
import ifsp.edu.view.fornecedores.WindowSubmenuFornecedores;
import ifsp.edu.view.produtos.WindowSubmenuProdutos;
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
        WindowSubmenuProdutos window = new WindowSubmenuProdutos();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
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

