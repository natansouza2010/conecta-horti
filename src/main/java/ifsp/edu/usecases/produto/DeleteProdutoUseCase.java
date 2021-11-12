package ifsp.edu.usecases.produto;

import ifsp.edu.model.Produto;
import ifsp.edu.utils.EntidadeNaoEncontradaException;

public class DeleteProdutoUseCase {

    private ProdutoDAO dao;

    public DeleteProdutoUseCase(ProdutoDAO dao) {
        this.dao = dao;
    }

    public boolean delete(Integer id){
        if(id == null || dao.findById(id).isEmpty()){
            throw new EntidadeNaoEncontradaException("Id nulo ou inexistente.");
        }
        return dao.delete(id);
    }

    public boolean delete(Produto produto){
        if(produto == null || dao.findOne(produto.getId()) == null){
            throw new EntidadeNaoEncontradaException("Produto não encontrado.");
        }
        return dao.delete(produto.getId());
    }


}
