package ifsp.edu.view.pedidos;

import ifsp.edu.controller.pedidos.CtlrCadatroPedidos;
import ifsp.edu.controller.pedidos.CtlrSubmenuPedidos;
import ifsp.edu.model.Pedido;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowCadastroPedidos {

    private CtlrCadatroPedidos controller;
    private Pedido pedido;


    public void show() throws IOException{
        show(null);
    }

    public void show(Pedido pedido) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        Pane graph = loader.load(getClass().getResource("FXMLCadastroPedidos.fxml").openStream());
        controller = loader.getController();

        Scene scene = new Scene(graph, 680, 400);
        Stage stage = new Stage();

        if (pedido != null) {
            stage.setTitle("Editar Pedido");
            controller.savePedido();
        } else{
            stage.setTitle("Novo Pedido");
        }

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }
}
