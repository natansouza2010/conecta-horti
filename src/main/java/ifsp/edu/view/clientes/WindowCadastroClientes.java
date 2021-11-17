package ifsp.edu.view.clientes;

import ifsp.edu.controller.clientes.CtrlCadastroClientes;
import ifsp.edu.model.Cliente;
import ifsp.edu.usecases.cliente.ClienteDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowCadastroClientes {

    private CtrlCadastroClientes controller;
    private Cliente cliente;

    public void show() throws IOException{
        show(null);
    }

    public void show(Cliente cliente) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        Pane graph = loader.load(getClass().getResource("FXMLCadastroCliente.fxml").openStream());
        controller = loader.getController();

        Scene scene = new Scene(graph, 680, 400);
        Stage stage = new Stage();

        if (cliente != null) {
            stage.setTitle("Editar Cliente");
            controller.setClientesToView(cliente);
        } else{
            stage.setTitle("Novo Cliente");
        }

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }
}
