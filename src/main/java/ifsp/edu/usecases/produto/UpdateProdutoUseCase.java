package ifsp.edu.usecases.produto;

import ifsp.edu.model.Produto;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class UpdateProdutoUseCase {
    private ProdutoDAO dao;

    public UpdateProdutoUseCase(ProdutoDAO dao) {
        this.dao = dao;
    }

    public boolean update(Produto produto){
        Validator<Produto> validator = new ProdutoValidator();
        Notification notification = validator.validate(produto);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        Integer id = produto.getId();
        if(dao.findById(id).isEmpty()){
            throw new EntidadeNaoEncontradaException("Produto não encontrado.");
        }

        return dao.update(produto);
    }
}
