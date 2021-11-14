package ifsp.edu.usecases.renda;

import ifsp.edu.model.Renda;

import java.time.LocalDate;
import java.util.List;

public class VisualizarRendaUseCase {

    private RendaDAO dao;

    public VisualizarRendaUseCase(RendaDAO dao) {
        this.dao = dao;
    }

    public List<Renda> findByDate(Integer id, LocalDate dataInicial, LocalDate dataFinal){
        if(id == null){
            throw new IllegalArgumentException("Id não pode ser nulo.");
        }
        if(dataInicial == null){
            throw new IllegalArgumentException("Data inicial não pode ser nula.");
        }
        if(dataFinal == null){
            throw new IllegalArgumentException("Data final não pode ser nula.");
        }
        if(dao.findById(id).isPresent()){
            return dao.listAll();
        }
        return null;
    }
}
