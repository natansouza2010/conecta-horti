
package ifsp.edu.controller;

import ifsp.edu.model.Cliente;
import ifsp.edu.view.WindowSubmenuClientes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
            window.showAndWait();
        } catch (IOException e ){
            e.printStackTrace();
        }
        System.out.println("oi");
    }

    public void submenuProdutos(ActionEvent actionEvent) {
    }

    public void submenuFornecedores(ActionEvent actionEvent) {
    }

    public void submenuRenda(ActionEvent actionEvent) {
    }

    public void submenuPedidos(ActionEvent actionEvent) {
    }

    public void submenuCatalogo(ActionEvent actionEvent) {
    }
}

