package ifsp.edu.view;

import ifsp.edu.controller.CtlrSubmenuClientes;
import ifsp.edu.controller.CtlrSubmenuFornecedores;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowSubmenuFornecedores {

    private CtlrSubmenuFornecedores controller;

    public void show() throws IOException{

        FXMLLoader loader = new FXMLLoader();

        Pane graph = loader.load(getClass().getResource("FXMLSubmenuFornecedores.fxml").openStream());
        controller = loader.getController();

        Scene scene = new Scene(graph, 680, 400);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }
}
