package ifsp.edu.usecases.produto;

import ifsp.edu.model.Produto;

import java.util.List;
import java.util.Optional;

public class FindProdutoUseCase {

    private ProdutoDAO dao;

    public FindProdutoUseCase(ProdutoDAO dao) {
        this.dao = dao;
    }

    public Optional<Produto> findOne(Integer id){
        if(id == null){
            throw new IllegalArgumentException("Id não pod ser nulo.");
        }
        return dao.findById(id);
    }

    public List<Produto> findAll(){
        return dao.listAll();
    }
}
