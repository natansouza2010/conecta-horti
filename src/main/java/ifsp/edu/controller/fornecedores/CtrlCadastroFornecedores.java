package ifsp.edu.controller.fornecedores;

import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.view.fornecedores.WindowSubmenuFornecedores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CtrlCadastroFornecedores {

    @FXML TextField txtCnpjFornecedor;
    @FXML TextField txtNomeFornecedor;
    @FXML TextField txtTel1Fornecedor;
    @FXML TextField txtTel2Fornecedor;
    @FXML TextField txtEnderecoFornecedor;
    @FXML TextField txtRazaoSocial;
    @FXML Button btnCadastrarFornecedor;

    public void cadastrarFornecedor(ActionEvent actionEvent) {
        WindowSubmenuFornecedores window = new WindowSubmenuFornecedores();
        try {
            window.show();
            FornecedorRepository dao = new FornecedorRepository();
            Fornecedor fornecedor = getFornecedorFromView();
            dao.insert(fornecedor);
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void btnVoltar(ActionEvent actionEvent) {
        WindowSubmenuFornecedores window = new WindowSubmenuFornecedores();
        try {
            window.show();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }



    private Fornecedor getFornecedorFromView(){
        String cnpj = String.valueOf(txtCnpjFornecedor.getText());
        String nome = String.valueOf(txtNomeFornecedor.getText());
        String tel1 = String.valueOf(txtTel1Fornecedor.getText());
        String tel2 = String.valueOf(txtTel2Fornecedor.getText());
        String end = String.valueOf(txtEnderecoFornecedor.getText());
        String razao = String.valueOf(txtRazaoSocial.getText());

        Fornecedor fornecedor = new Fornecedor(cnpj,nome,tel1,tel2,end,razao);
        return fornecedor;


    }
}
