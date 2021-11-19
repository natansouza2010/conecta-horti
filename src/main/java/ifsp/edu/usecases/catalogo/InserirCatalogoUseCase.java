package ifsp.edu.usecases.catalogo;

import ifsp.edu.model.Catalogo;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class InserirCatalogoUseCase {

    private CatalogoDAO dao;
    private ProdutoDAO daoProduto;

    public InserirCatalogoUseCase(CatalogoDAO dao) {
        this.dao = dao;
    }

    public boolean insert(Catalogo catalogo){
        Validator<Catalogo> validator = new CatalogoValidator();
        Notification notification = validator.validate(catalogo);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        Integer idProduto = catalogo.getProduto().getId();
        if(daoProduto.findById(idProduto).isEmpty()){
            throw new EntidadeNaoEncontradaException("Id do produto selecionado não encontrado.");
        }

        if(dao.findDataInicial(catalogo.getDataInicial()).isPresent()){
            throw new EntidadeExistenteException("Data inicial já existe.");
        }

        if(dao.findDataFinal(catalogo.getDataFinal()).isPresent()){
            throw new EntidadeExistenteException("Data final já existe.");
        }

        return dao.insert(catalogo);
    }
}
