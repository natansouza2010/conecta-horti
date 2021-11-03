module ifsp.edu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens ifsp.edu.view to javafx.fxml;
    opens ifsp.edu.model to javafx.fxml;
    opens ifsp.edu.controller to javafx.fxml;

    exports ifsp.edu.controller;
    exports ifsp.edu.view;
    exports ifsp.edu.model;

}