module ifsp.edu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens ifsp.edu.view to javafx.fxml;
    opens ifsp.edu.model to javafx.fxml;

    exports ifsp.edu.view;
    exports ifsp.edu.model;
    exports ifsp.edu.view.clientes;
    opens ifsp.edu.view.clientes to javafx.fxml;

    exports ifsp.edu.view.fornecedores;
    opens ifsp.edu.view.fornecedores to javafx.fxml;

    exports ifsp.edu.view.principal;
    opens ifsp.edu.view.principal to javafx.fxml;

    exports ifsp.edu.controller.principal;
    opens ifsp.edu.controller.principal to javafx.fxml;

    exports ifsp.edu.controller.clientes;
    opens ifsp.edu.controller.clientes to javafx.fxml;

    exports ifsp.edu.controller.fornecedores;
    opens ifsp.edu.controller.fornecedores to javafx.fxml;

    exports ifsp.edu.view.produtos;
    opens ifsp.edu.view.produtos to javafx.fxml;

    exports ifsp.edu.controller.produtos;
    opens ifsp.edu.controller.produtos to javafx.fxml;

    exports ifsp.edu.view.pedidos;
    opens ifsp.edu.view.pedidos to javafx.fxml;

    exports ifsp.edu.controller.pedidos;
    opens ifsp.edu.controller.pedidos to javafx.fxml;

}