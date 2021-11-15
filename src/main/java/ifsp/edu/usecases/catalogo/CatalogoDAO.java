package ifsp.edu.usecases.catalogo;

import ifsp.edu.dao.DAO;
import ifsp.edu.model.Catalogo;
import ifsp.edu.model.Produto;

import java.util.Optional;


public interface CatalogoDAO extends DAO<Catalogo, Integer> {
    Optional<Produto> findProdutoById(Integer id);
    Optional<Produto> findProdutoByName(String nome);
}
