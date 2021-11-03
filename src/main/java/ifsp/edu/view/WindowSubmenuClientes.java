package ifsp.edu.view;

import ifsp.edu.controller.CtlrSubmenuClientes;
import ifsp.edu.model.Cliente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowSubmenuClientes {

    private CtlrSubmenuClientes controller;

    public void showAndWait() throws IOException{

        FXMLLoader loader = new FXMLLoader();

        Pane graph = loader.load(getClass().getResource("FXMLSubmenuClientes.fxml").openStream());
        controller = loader.getController();

        Scene scene = new Scene(graph, 600, 400);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }
}
