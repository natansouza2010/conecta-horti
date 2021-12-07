package ifsp.edu.view.produtos;

import ifsp.edu.controller.produtos.CtrlCadatroProdutos;
import ifsp.edu.controller.produtos.CtrlSubmenuProdutos;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;
import javafx.fxml.FXMLLoader;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowCadastroProdutos {

    private CtrlCadatroProdutos controller;
    private Produto produto;

    public void show() throws IOException{
        show(null);
    }

    public void show(Produto produto) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        Pane graph = loader.load(getClass().getResource("FXMLCadastroProdutos.fxml").openStream());
        controller = loader.getController();

        Scene scene = new Scene(graph, 680, 400);
        Stage stage = new Stage();

        if (produto != null) {
            stage.setTitle("Editar Produto");
            controller.setProdutoToView(produto);
        } else{
            stage.setTitle("Novo Produto");
        }

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }
}
