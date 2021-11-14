package ifsp.edu.usecases.renda;

import ifsp.edu.dao.DAO;
import ifsp.edu.model.Renda;

import java.util.Optional;

public interface RendaDAO extends DAO<Renda, Integer> {
    Optional<Renda> findById(Integer id);
}
