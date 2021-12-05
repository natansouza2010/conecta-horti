package ifsp.edu.controller.clientes;

import ifsp.edu.model.Cliente;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.repository.ClienteRepository;
import ifsp.edu.sqlitedao.ClienteDAOImpl;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.usecases.cliente.DeleteClienteUseCase;
import ifsp.edu.usecases.cliente.FindClienteUseCase;
import ifsp.edu.usecases.cliente.InserirClienteUseCase;
import ifsp.edu.view.clientes.WindowCadastroClientes;
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

public class CtlrSubmenuClientes {

    @FXML Button btnAdicionarCliente;
    @FXML Button btnRemoverCliente;
    @FXML Button btnEditarCliente;
    @FXML Button btnBuscarCliente;
    @FXML TextField txtBuscarCliente;

    @FXML TableView<Cliente> table;

    @FXML TableColumn colCpfCliente;
    @FXML TableColumn colNomeCliente;
    @FXML TableColumn colTel1Cliente;
    @FXML TableColumn colTel2Cliente;
    @FXML TableColumn colEnderecoCliente;

    ObservableList<Cliente> clientes;

    //dao | usecases
    ClienteDAO clienteDAO = new ClienteDAOImpl();
    private DeleteClienteUseCase deleteClienteUseCase;
    private FindClienteUseCase findClienteUseCase;

    public void initialize(){
        colCpfCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
        colNomeCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        colTel1Cliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone1"));
        colTel2Cliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone2"));
        colEnderecoCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("endereco"));

        clientes = FXCollections.observableArrayList();
        loadTable();
        table.setItems(clientes);
    }

    private void loadTable(){
        List<Cliente> clienteArrayList = new ArrayList<>(clienteDAO.listAll());
        clientes = FXCollections.observableArrayList(clienteArrayList);
    }

    private void reloadTable(){
        clientes.clear();
        loadTable();
        table.setItems(clientes);
    }

    public void adicionarCliente(ActionEvent actionEvent) throws IOException {
        WindowCadastroClientes window = new WindowCadastroClientes();
        try {
            window.show();
            reloadTable();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void removerCliente(ActionEvent actionEvent) {
        Cliente cliente = table.getSelectionModel().getSelectedItem();
        deleteClienteUseCase = new DeleteClienteUseCase(clienteDAO);
        deleteClienteUseCase.delete(cliente.getCpf());

        reloadTable();
    }

    public void editarCliente(ActionEvent actionEvent) {
       final Cliente selectedItem = table.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            WindowCadastroClientes window = new WindowCadastroClientes();
            try {
                window.show(selectedItem);
                reloadTable();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void buscarCliente(ActionEvent actionEvent) {
        String cpf = String.valueOf(txtBuscarCliente.getText());
        findClienteUseCase = new FindClienteUseCase(clienteDAO);
        Optional<Cliente> one = findClienteUseCase.findOne(cpf);
        clientes.clear();
        clientes.add(one.get());
        table.setItems(clientes);
    }

    public void voltar(ActionEvent actionEvent) {
        close();
    }

    private void close(){
        Stage stage = (Stage) btnAdicionarCliente.getScene().getWindow();
        stage.close();
    }


}
