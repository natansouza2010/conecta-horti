package ifsp.edu.view.catalogo;

import ifsp.edu.controller.catalogo.CtrlCadastroCatalogo;
import ifsp.edu.controller.clientes.CtrlCadastroClientes;
import ifsp.edu.model.Catalogo;
import ifsp.edu.model.Cliente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowCadastroCatalogo {

    private CtrlCadastroCatalogo controller;
    private Catalogo catalogo;

    public void show() throws IOException {
        show(null);
    }

    public void show(Catalogo catalogo) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        Pane graph = loader.load(getClass().getResource("FXMLCadastroCatalogo.fxml").openStream());
        controller = loader.getController();

        Scene scene = new Scene(graph, 680, 400);
        Stage stage = new Stage();

        if (catalogo == null) {
            stage.setTitle("Novo Catálogo");
        }

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }
}
