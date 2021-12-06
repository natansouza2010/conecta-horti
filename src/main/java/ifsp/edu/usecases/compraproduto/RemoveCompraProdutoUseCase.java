package ifsp.edu.usecases.compraproduto;

import ifsp.edu.model.CompraProduto;
import ifsp.edu.utils.EntidadeNaoEncontradaException;

public class RemoveCompraProdutoUseCase {
    private CompraProdutoDAO dao;

    public RemoveCompraProdutoUseCase(CompraProdutoDAO dao) {
        this.dao = dao;
    }

    public boolean delete(CompraProduto compra){

        if(compra == null && dao.findByCompra(compra).isEmpty())
            throw new EntidadeNaoEncontradaException("Compra é nula");

        return dao.delete(compra.getId());
    }
}
