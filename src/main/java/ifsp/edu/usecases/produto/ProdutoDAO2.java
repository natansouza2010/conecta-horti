package ifsp.edu.usecases.produto;

import ifsp.edu.dao.DAO;
import ifsp.edu.model.Produto;

import java.util.List;

public class ProdutoDAO2 implements DAO<Produto,Integer> {
    @Override
    public boolean insert(Produto obj) {

        return true;
    }

    @Override
    public Produto findOne(Integer key) {
        return null;
    }

    @Override
    public List<Produto> listAll() {
        return null;
    }

    @Override
    public boolean update(Produto obj) {
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }
}
