package ifsp.edu.controller.clientes;

import ifsp.edu.model.Cliente;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.repository.ClienteRepository;
import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.usecases.cliente.InserirClienteUseCase;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;
import ifsp.edu.usecases.fornecedor.InsertFornecedorUseCase;
import ifsp.edu.view.clientes.WindowSubmenuClientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CtrlCadastroClientes {

    @FXML TextField txtCpfCliente;
    @FXML TextField txtNomeCliente;
    @FXML TextField txtTel1Cliente;
    @FXML TextField txtTel2Cliente;
    @FXML TextField txtEnderecoCliente;
    @FXML Button btnCadastrarCliente;

    private Cliente cliente;
    private InserirClienteUseCase inserirClienteUseCase;

    public void saveOrUpdate(ActionEvent actionEvent) {
        saveOrUpdate();
        close();
    }

    public void btnVoltar(ActionEvent actionEvent) {
        close();
    }

    private Cliente getClienteFromView(){
        String cpf = String.valueOf(txtCpfCliente.getText());
        String nome = String.valueOf(txtNomeCliente.getText());
        String tel1 = String.valueOf(txtTel1Cliente.getText());
        String tel2 = String.valueOf(txtTel2Cliente.getText());
        String end = String.valueOf(txtEnderecoCliente.getText());

        Cliente cliente = new Cliente(cpf,nome,end,tel1,tel2);
        return cliente;
    }

    public void setClientesToView(Cliente c) {
        cliente=c;
        txtCpfCliente.setText(c.getCpf());
        txtEnderecoCliente.setText(c.getEndereco());
        txtNomeCliente.setText(c.getNome());
        txtTel1Cliente.setText(c.getTelefone1());
        txtTel2Cliente.setText(c.getTelefone2());
    }

    public void saveOrUpdate() throws RuntimeException{
        Cliente a = getClienteFromView();
        if (cliente == null && a != null ) {
            save(a);
        } else if (cliente != null && a != null ) {
            update(getClienteFromView());
        }
        Stage stage = (Stage) txtEnderecoCliente.getScene().getWindow();
    }



    private void update(Cliente c) {
        ClienteRepository dao = new ClienteRepository();
        dao.update(c);
    }

    private void save(Cliente c) {
        ClienteDAO dao = new ClienteRepository();
        inserirClienteUseCase = new InserirClienteUseCase(dao);
        inserirClienteUseCase.insert(c);
    }

    private void close(){
        Stage stage = (Stage) txtCpfCliente.getScene().getWindow();
        stage.close();
    }

    public void cadastrarCliente(ActionEvent actionEvent) {
        WindowSubmenuClientes window = new WindowSubmenuClientes();
        try {
            window.show();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }
}
