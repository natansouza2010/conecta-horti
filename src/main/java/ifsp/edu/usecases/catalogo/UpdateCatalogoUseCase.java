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
    private ProdutoDAO daoProduto;

    public UpdateCatalogoUseCase(CatalogoDAO dao) {
        this.dao = dao;
    }

//    public boolean updateProduto(Catalogo catalogo,Produto produto){
//
//        if(daoProduto.findOne(produto.getId()) == null){
//            throw new EntidadeNaoEncontradaException("Produto inexistente");
//        }
//
//        return dao.updateProdutoDoCatalogo(catalogo,produto);
//    }
}
