package ifsp.edu.usecases.compraproduto;

import ifsp.edu.dao.DAO;
import ifsp.edu.model.CompraProduto;
import ifsp.edu.model.Fornecedor;

import java.util.Optional;

public interface CompraProdutoDAO extends DAO<CompraProduto, Integer> {
    Optional<CompraProduto> findByCompra(CompraProduto compra);
}
