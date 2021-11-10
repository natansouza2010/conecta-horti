package ifsp.edu.controller.pedidos;

import ifsp.edu.view.pedidos.WindowCadastroPedidos;
import ifsp.edu.view.pedidos.WindowSubmenuPedidos;
import ifsp.edu.view.principal.WindowPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CtlrCadatroPedidos {

    @FXML ChoiceBox cbProdutos;
    @FXML ChoiceBox cbCpfCliente;
    @FXML TextField txtQuantidadeProduto;
    @FXML Label txtValorTotal;
    @FXML TableColumn colIdProduto;
    @FXML TableColumn colQuantidadeProduto;
    @FXML TableColumn colValorProduto;
    @FXML Button btnAddProduto;

    public void cadastrarPedido(ActionEvent actionEvent) {
        WindowSubmenuPedidos window = new WindowSubmenuPedidos();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void voltarMenu(ActionEvent actionEvent) {
        WindowSubmenuPedidos window = new WindowSubmenuPedidos();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}
