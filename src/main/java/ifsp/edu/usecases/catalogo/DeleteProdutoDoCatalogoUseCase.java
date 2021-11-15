package ifsp.edu.usecases.catalogo;

import ifsp.edu.model.Cliente;
import ifsp.edu.model.Produto;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.utils.EntidadeNaoEncontradaException;

public class DeleteProdutoDoCatalogoUseCase {

    private CatalogoDAO daoCatalogo;

    public DeleteProdutoDoCatalogoUseCase(CatalogoDAO daoCatalogo) {
        this.daoCatalogo = daoCatalogo;
    }

    public boolean delete(Produto produto){
        if(produto == null || daoCatalogo.findProdutoByName(produto.getNome()).isEmpty()){
            throw new EntidadeNaoEncontradaException("O produto com esse nome não está no catálogo.");
        }


        return daoCatalogo.delete(produto.getId());
    }
}
