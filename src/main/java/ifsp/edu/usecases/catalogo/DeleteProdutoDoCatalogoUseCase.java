package ifsp.edu.usecases.catalogo;

import ifsp.edu.model.Catalogo;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Produto;
import ifsp.edu.usecases.cliente.ClienteDAO;
import ifsp.edu.usecases.produto.ProdutoDAO;
import ifsp.edu.utils.EntidadeNaoEncontradaException;

import java.time.LocalDate;

public class DeleteProdutoDoCatalogoUseCase {

    private CatalogoDAO daoCatalogo;

    public DeleteProdutoDoCatalogoUseCase(CatalogoDAO daoCatalogo) {
        this.daoCatalogo = daoCatalogo;
    }

    public boolean delete(Catalogo catalago){

        if(catalago == null){
            throw new EntidadeNaoEncontradaException("O catalogo está nulo.");
        }


        return daoCatalogo.delete(catalago.getId());
    }
}
