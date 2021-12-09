package ifsp.edu.controller.catalogo;

import ifsp.edu.model.Catalogo;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;
import ifsp.edu.repository.CatalogoRepository;
import ifsp.edu.repository.FornecedorRepository;
import ifsp.edu.repository.ProdutoRepository;
import ifsp.edu.sqlitedao.CatalogoDAOImpl;
import ifsp.edu.usecases.catalogo.CatalogoDAO;
import ifsp.edu.usecases.catalogo.DeleteProdutoDoCatalogoUseCase;
import ifsp.edu.usecases.catalogo.InserirCatalogoUseCase;
import ifsp.edu.usecases.catalogo.UpdateCatalogoUseCase;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;
import ifsp.edu.usecases.produto.DeleteProdutoUseCase;
import ifsp.edu.usecases.produto.FindProdutoUseCase;
import ifsp.edu.usecases.produto.InserirProdutoUseCase;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.view.catalogo.WindowCadastroCatalogo;
import ifsp.edu.view.principal.WindowPrincipal;
import ifsp.edu.view.produtos.WindowCadastroProdutos;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CtrlSubmenuCatalogo {

    @FXML Button btnCriarCatalogo;
    @FXML Button btnRemoveCatalogo;

    @FXML
    TableView<Catalogo> tableCatalogo;

    ObservableList<Catalogo> catalogos;

    @FXML
    TableColumn<Catalogo,Integer> colIdCatalogo;
    @FXML TableColumn<Catalogo, LocalDate> colDataInicial;
    @FXML TableColumn<Catalogo, LocalDate> colDataFinal;


    private InserirCatalogoUseCase inserirCatalogoUseCase;
    private DeleteProdutoDoCatalogoUseCase deleteProdutoDoCatalogoUseCase;
    private UpdateCatalogoUseCase updateCatalogoUseCase;

    public void initialize(){
        colIdCatalogo.setCellValueFactory(new PropertyValueFactory<Catalogo,Integer>("id"));
        colDataInicial.setCellValueFactory(new PropertyValueFactory<Catalogo,LocalDate>("dataInicial"));
        colDataFinal.setCellValueFactory(new PropertyValueFactory<Catalogo,LocalDate>("dataFinal"));
//

        catalogos = FXCollections.observableArrayList();
        loadTable();
        tableCatalogo.setItems(catalogos);

    }

    private void loadTable() {
        CatalogoDAO dao = new CatalogoDAOImpl();
        List<Catalogo> cat = new ArrayList<>(dao.listAll());
        catalogos = FXCollections.observableArrayList(cat);

    }

    private void reloadTable(){
        catalogos.clear();
        loadTable();
        tableCatalogo.setItems(catalogos);
    }



    public void voltar(ActionEvent actionEvent) {
        close();
    }

    public void close(){
        Stage stage = (Stage) btnRemoveCatalogo.getScene().getWindow();
        stage.close();
    }

    public void criarCatalago(ActionEvent actionEvent) {
        WindowCadastroCatalogo window = new WindowCadastroCatalogo();
        try {
            window.show();
            reloadTable();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void removerCatalago(ActionEvent actionEvent) {
        Catalogo catalogo = tableCatalogo.getSelectionModel().getSelectedItem();
        CatalogoDAO dao = new CatalogoDAOImpl();
        deleteProdutoDoCatalogoUseCase = new DeleteProdutoDoCatalogoUseCase(dao);
        deleteProdutoDoCatalogoUseCase.delete(catalogo);
        reloadTable();
    }
}
