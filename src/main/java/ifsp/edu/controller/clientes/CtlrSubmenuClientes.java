package ifsp.edu.controller.clientes;

import ifsp.edu.model.Cliente;
import ifsp.edu.model.Cliente;
import ifsp.edu.repository.ClienteRepository;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.usecases.cliente.DeleteClienteUseCase;
import ifsp.edu.usecases.cliente.FindClienteUseCase;
import ifsp.edu.usecases.cliente.InserirClienteUseCase;
import ifsp.edu.view.clientes.WindowCadastroClientes;
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
    private DeleteClienteUseCase deleteClienteUseCase;
    private FindClienteUseCase findClienteUseCase;
    private InserirClienteUseCase inserirClienteUseCase;

    public void initialize(){
        colCpfCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
        colNomeCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        colTel1Cliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone1"));
        colTel2Cliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone2"));
        colEnderecoCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("endereco"));
        ClienteDAO dao = new ClienteRepository();
        inserirClienteUseCase = new InserirClienteUseCase(dao);
        Cliente c1 = new Cliente("123456789","Cliente 1","Rua das Torres","33064412","33064413");
        Cliente c2 = new Cliente("987654321","Cliente 2","Rua Abilo Rodrigues","33264422","33064111");
        Cliente c3 = new Cliente("111222333","Cliente 3","Rua Carlos del Nero","12313123","33012311");
        inserirClienteUseCase.insert(c1);
        inserirClienteUseCase.insert(c2);
        inserirClienteUseCase.insert(c3);
        clientes = FXCollections.observableArrayList();
        loadTable();

        table.setItems(clientes);
    }

    private void loadTable(){
        ClienteRepository dao = new ClienteRepository();
        List<Cliente> clienteArrayList = new ArrayList<>(dao.listAll());
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

        ClienteDAO dao = new ClienteRepository();
        deleteClienteUseCase = new DeleteClienteUseCase(dao);
        deleteClienteUseCase.delete(cliente.getCpf());

        reloadTable();
    }

    public void editarCliente(ActionEvent actionEvent) {
        WindowCadastroClientes window = new WindowCadastroClientes();
        try {
            window.show();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void buscarCliente(ActionEvent actionEvent) {
        String cpf = String.valueOf(txtBuscarCliente.getText());

        ClienteDAO dao = new ClienteRepository();

        findClienteUseCase = new FindClienteUseCase(dao);

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
