package ifsp.edu.view.fornecedores;

import ifsp.edu.controller.fornecedores.CtrlCadastroFornecedores;
import ifsp.edu.model.Fornecedor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowCadastroFornecedores {

    private CtrlCadastroFornecedores controller;
    private Fornecedor fornecedor;

    public void show() throws IOException{
        show(null);
    }

    public void show(Fornecedor fornecedor) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        Pane graph = loader.load(getClass().getResource("FXMLCadastroFornecedores.fxml").openStream());
        controller = loader.getController();

        Scene scene = new Scene(graph, 680, 400);
        Stage stage = new Stage();

        if (fornecedor != null) {
            stage.setTitle("Editar Fornecedor");
            controller.setFornecedoresToView(fornecedor);
        } else{
            stage.setTitle("Novo Fornecedor");
        }

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }
}
