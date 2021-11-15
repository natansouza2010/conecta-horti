package ifsp.edu.usecases.catalogo;

import ifsp.edu.model.Catalogo;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class InserirCatalogoUseCase {

    private CatalogoDAO dao;

    public InserirCatalogoUseCase(CatalogoDAO dao) {
        this.dao = dao;
    }

    public boolean insert(Catalogo catalogo){
        Validator<Catalogo> validator = new CatalogoValidator();
        Notification notification = validator.validate(catalogo);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        Integer idCatalogo = catalogo.getId();
        if(dao.findOne(idCatalogo)!= null){
            throw new EntidadeExistenteException(notification.errorMessage());
        }

        Integer idProduto = catalogo.getProduto().getId();
        if(dao.findProdutoById(idProduto).isEmpty()){
            throw new EntidadeNaoEncontradaException("Id do produto selecionado não encontrado.");
        }
        return dao.insert(catalogo);
    }
}
