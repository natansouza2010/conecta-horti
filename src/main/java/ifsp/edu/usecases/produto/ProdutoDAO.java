package ifsp.edu.usecases.produto;

import ifsp.edu.dao.DAO;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;

import java.util.Optional;

public interface ProdutoDAO extends DAO <Produto, Integer>  {
    Optional<Produto> findById(Integer id);
    Produto findByNome(String nome);
    Optional<Produto> findByFornecedor(String cnpj);
    Fornecedor findFornecedorByProduto(Produto produto);
}
