package ifsp.edu.controller.pedidos;

import ifsp.edu.view.pedidos.WindowCadastroPedidos;
import ifsp.edu.view.pedidos.WindowSubmenuPedidos;
import ifsp.edu.view.principal.WindowPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class CtlrSubmenuPedidos {

    @FXML Label txtNomeClientePedido;
    @FXML Label txtCpfClientePedido;
    @FXML Label txtEnderecoClientePedido;
    @FXML Label txtTel1ClientePedido;
    @FXML Label txtEnderecoCliente;

    public void voltarMenu(ActionEvent actionEvent) {
        WindowPrincipal window = new WindowPrincipal();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void atualizarStatus(ActionEvent actionEvent) {
        WindowCadastroPedidos window = new WindowCadastroPedidos();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void cadastrarPedido(ActionEvent actionEvent) {
        WindowCadastroPedidos window = new WindowCadastroPedidos();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}
