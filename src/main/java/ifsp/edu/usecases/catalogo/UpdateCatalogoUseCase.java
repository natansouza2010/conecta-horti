package ifsp.edu.usecases.catalogo;

import ifsp.edu.model.Catalogo;
import ifsp.edu.model.Produto;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.usecases.produto.ProdutoValidator;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class UpdateCatalogoUseCase {

    private CatalogoDAO dao;

    public UpdateCatalogoUseCase(CatalogoDAO dao) {
        this.dao = dao;
    }

    public boolean update(Catalogo catalogo){
        Validator<Catalogo> validator = new CatalogoValidator();
        Notification notification = validator.validate(catalogo);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        Integer idCatalogo = catalogo.getId();
        if(dao.findOne(idCatalogo)==null){
            throw new EntidadeNaoEncontradaException("Id do catálogo não encontrado.");
        }

        Integer idProduto = catalogo.getProduto().getId();
        if(dao.findProdutoById(idProduto).isEmpty()){
            throw new EntidadeNaoEncontradaException("Id do produto a ser inserido no catálogo não foi encontrado.");
        }

        return dao.update(catalogo);
    }
}
