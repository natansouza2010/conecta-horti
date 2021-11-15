package ifsp.edu.usecases.fornecedor;

import ifsp.edu.dao.DAO;
import ifsp.edu.model.Fornecedor;

import java.util.*;

public interface FornecedorDAO extends DAO<Fornecedor, String> {
    Optional<Fornecedor> findByCNPJ(String cnpj);
}
