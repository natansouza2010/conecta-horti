package ifsp.edu.controller.fornecedores;

import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.view.fornecedores.WindowCadastroFornecedores;
import ifsp.edu.view.principal.WindowPrincipal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CtlrSubmenuFornecedores {


    @FXML
    Button btnAdicionarFornecedor;
    @FXML Button btnRemoverFornecedor;
    @FXML Button btnEditarFornecedor;
    @FXML Button btnBuscarFornecedor;
    @FXML TextField txtBuscarFornecedor;

    @FXML TableView<Fornecedor> table;

    @FXML TableColumn<Fornecedor,String> colCnpjFornecedor;
    @FXML TableColumn<Fornecedor, String> colNomeFornecedor;
    @FXML TableColumn<Fornecedor, String> colTel1Fornecedor;
    @FXML TableColumn<Fornecedor, String> colTel2Fornecedor;
    @FXML TableColumn<Fornecedor, String> colEnderecoFornecedor;
    @FXML TableColumn<Fornecedor, String> colRazaoFornecedor;

    ObservableList<Fornecedor> fornecedores;


    public void initialize(){
        colCnpjFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("cnpj"));
        colNomeFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("nome"));
        colTel1Fornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("telefone1"));
        colTel2Fornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("telefone2"));
        colEnderecoFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("endereco"));
        colRazaoFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("razaoSocial"));
        fornecedores = FXCollections.observableArrayList();
        loadTable();

        table.setItems(fornecedores);


    }
    private void loadTable(){
        FornecedorRepository dao = new FornecedorRepository();
        List<Fornecedor> forn = new ArrayList<>(dao.listAll());
        fornecedores = FXCollections.observableArrayList(forn);
    }

    private void reloadTable(){
        fornecedores.clear();
        loadTable();
        table.setItems(fornecedores);

    }




    public void adicionarFornecedor(ActionEvent actionEvent) {
        WindowCadastroFornecedores window = new WindowCadastroFornecedores();
        try {
            window.show();
            reloadTable();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void removerFornecedor(ActionEvent actionEvent) {
        Fornecedor fornecedor = table.getSelectionModel().getSelectedItem();
        FornecedorRepository dao = new FornecedorRepository();
        dao.delete(fornecedor.getCnpj());
        reloadTable();

    }

    public void editarFornecedor(ActionEvent actionEvent) {
        WindowCadastroFornecedores window = new WindowCadastroFornecedores();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void buscarFornecedor(ActionEvent actionEvent) {
        String cnpj = String.valueOf(txtBuscarFornecedor.getText());
        FornecedorRepository dao = new FornecedorRepository();
        Fornecedor one = dao.findOne(cnpj);
        fornecedores.clear();
        fornecedores.add(one);
        table.setItems(fornecedores);

    }

    public void voltarParaMenu(ActionEvent actionEvent) {
        WindowPrincipal window = new WindowPrincipal();
        try {
            window.show();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }
}
