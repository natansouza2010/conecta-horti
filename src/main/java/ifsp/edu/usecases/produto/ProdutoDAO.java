package ifsp.edu.usecases.produto;

import ifsp.edu.dao.DAO;
import ifsp.edu.model.Produto;

import java.util.Optional;

public interface ProdutoDAO extends DAO <Produto, Integer>  {
    Optional<Produto> findById(Integer id);
    Optional<Produto> findByFornecedor(String cnpj);
}
