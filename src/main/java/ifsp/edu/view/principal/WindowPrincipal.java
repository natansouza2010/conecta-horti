package ifsp.edu.view.principal;

import ifsp.edu.controller.principal.CtlrMenuPrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowPrincipal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent grafic = FXMLLoader.load(getClass().getResource("FXMLWindowPrincipal.fxml"));
        Scene scene = new Scene(grafic, 680,400 );
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private CtlrMenuPrincipal controller;
    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane graph = loader.load(getClass().getResource("FXMLWindowPrincipal.fxml").openStream());
        controller = loader.getController();

        Scene scene = new Scene(graph, 600, 400);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();

    }
}
