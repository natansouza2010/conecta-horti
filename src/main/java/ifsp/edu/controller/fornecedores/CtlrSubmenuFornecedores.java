package ifsp.edu.controller.fornecedores;

import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.sqlitedao.FornecedorDAOImpl;
import ifsp.edu.usecases.fornecedor.DeleteFornecedorUseCase;
import ifsp.edu.usecases.fornecedor.FindFornecedorUseCase;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.usecases.fornecedor.InsertFornecedorUseCase;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CtlrSubmenuFornecedores {

    @FXML
    Button btnAdicionarFornecedor;
    @FXML Button btnRemoverFornecedor;
    @FXML Button btnEditarFornecedor;
    @FXML Button btnBuscarFornecedor;
    @FXML TextField txtBuscarFornecedor;
    @FXML Button btnVoltar;

    @FXML TableView<Fornecedor> table;

    @FXML TableColumn<Fornecedor,String> colCnpjFornecedor;
    @FXML TableColumn<Fornecedor, String> colNomeFornecedor;
    @FXML TableColumn<Fornecedor, String> colTel1Fornecedor;
    @FXML TableColumn<Fornecedor, String> colTel2Fornecedor;
    @FXML TableColumn<Fornecedor, String> colEnderecoFornecedor;
    @FXML TableColumn<Fornecedor, String> colRazaoFornecedor;

    ObservableList<Fornecedor> fornecedores;

    //dao | usecases
     FornecedorDAO fornecedorDAO = new FornecedorDAOImpl();
    private DeleteFornecedorUseCase deleteFornecedorUseCase;
    private FindFornecedorUseCase findFornecedorUseCase;

    public void initialize(){
        //atribuindo as colunas da interface com os atributos do model
        colCnpjFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("cnpj"));
        colNomeFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("nome"));
        colTel1Fornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("telefone1"));
        colTel2Fornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("telefone2"));
        colEnderecoFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("endereco"));
        colRazaoFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("razaoSocial"));

        //cria a observableList de fornecedores
        fornecedores = FXCollections.observableArrayList();

        //coloca o valor na observableList
        loadTable();

        //setta os dados da tabela
        table.setItems(fornecedores);
    }

    private void loadTable(){
        List<Fornecedor> forn = new ArrayList<>(fornecedorDAO.listAll());
        fornecedores = FXCollections.observableArrayList(forn);
    }

    //limpa e recarrega os dados
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
        deleteFornecedorUseCase = new DeleteFornecedorUseCase(fornecedorDAO);
        deleteFornecedorUseCase.delete(fornecedor.getCnpj());

        reloadTable();
    }

    public void editarFornecedor(ActionEvent actionEvent) {
        final Fornecedor selectedItem = table.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            WindowCadastroFornecedores window = new WindowCadastroFornecedores();
            try {
                window.show(selectedItem);
                reloadTable();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void buscarFornecedor(ActionEvent actionEvent) {
        String cnpj = String.valueOf(txtBuscarFornecedor.getText());

        findFornecedorUseCase = new FindFornecedorUseCase(fornecedorDAO);

        Optional<Fornecedor> one = findFornecedorUseCase.findOne(cnpj);
        fornecedores.clear();
        fornecedores.add(one.get());
        table.setItems(fornecedores);

    }

    public void voltarParaMenu(ActionEvent actionEvent) {
        close();
    }

    public void close(){
        Stage stage = (Stage) btnBuscarFornecedor.getScene().getWindow();
        stage.close();
    }
}
