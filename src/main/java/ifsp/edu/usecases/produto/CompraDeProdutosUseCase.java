package ifsp.edu.usecases.produto;

import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class CompraDeProdutosUseCase {

    private ProdutoDAO dao;

    public CompraDeProdutosUseCase(ProdutoDAO dao) {
        this.dao = dao;
    }

    public Fornecedor getFornecedor(Produto produto){
        Validator<Produto> validator = new ProdutoValidator();
        Notification notification = validator.validate(produto);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        Integer id = produto.getId();
        if(dao.findById(id).isEmpty()){
            throw new EntidadeNaoEncontradaException("Id do produto não encontrado.");
        }

        return dao.findFornecedorByProduto(produto);
    }
}
