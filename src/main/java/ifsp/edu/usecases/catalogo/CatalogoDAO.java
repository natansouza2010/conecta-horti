package ifsp.edu.usecases.catalogo;

import ifsp.edu.dao.DAO;
import ifsp.edu.model.Catalogo;
import ifsp.edu.model.Produto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface CatalogoDAO extends DAO<Catalogo, Integer>{
    boolean deleteProdutoDoCatalogo(LocalDate dataInicial, LocalDate dataFinal, Produto produto);
    boolean updateProdutoDoCatalogo(Catalogo catalogo, Produto produto);
    Optional<LocalDate> findDataFinal(LocalDate dataFinal);
    Optional<LocalDate> findDataInicial(LocalDate dataInicial);
    Optional<Produto> findProdutoByName(String nome);
}
