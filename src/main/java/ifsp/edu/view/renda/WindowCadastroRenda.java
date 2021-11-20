package ifsp.edu.view.renda;

import ifsp.edu.controller.clientes.CtrlCadastroClientes;
import ifsp.edu.controller.renda.CtrlCadastroRenda;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Renda;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowCadastroRenda {
    private CtrlCadastroRenda controller;
    private Renda renda;

    public void show() throws IOException{
        show(null);
    }

    public void show(Renda renda) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        Pane graph = loader.load(getClass().getResource("FXMLCadastroRenda.fxml").openStream());
        controller = loader.getController();

        Scene scene = new Scene(graph, 313, 370);
        Stage stage = new Stage();

        if (renda == null) {
            stage.setTitle("Nova Renda");
        }

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }
}
