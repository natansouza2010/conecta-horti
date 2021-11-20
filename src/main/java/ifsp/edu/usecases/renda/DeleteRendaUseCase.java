package ifsp.edu.usecases.renda;

import ifsp.edu.model.Produto;
import ifsp.edu.utils.EntidadeNaoEncontradaException;

public class DeleteRendaUseCase  {
    private RendaDAO dao;

    public DeleteRendaUseCase(RendaDAO dao) {
        this.dao = dao;
    }

    public boolean delete(Integer id){
        if(id == null || dao.findById(id).isEmpty()){
            throw new EntidadeNaoEncontradaException("Id nulo ou inexistente.");
        }
        return dao.delete(id);
    }



}
