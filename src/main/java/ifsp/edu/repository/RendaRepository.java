package ifsp.edu.repository;

import ifsp.edu.model.Produto;
import ifsp.edu.model.Renda;
import ifsp.edu.usecases.renda.RendaDAO;

import java.util.*;

public class RendaRepository implements RendaDAO {
    static Map<Integer, Renda> rendaMap = new HashMap<>();


    @Override
    public boolean insert(Renda obj) {
        rendaMap.put(obj.getId(),obj);
        return true;
    }

    @Override
    public Renda findOne(Integer key) {
        if(rendaMap.containsKey(key)){
            return rendaMap.get(key);
        }
        return null;
    }

    @Override
    public List<Renda> listAll() {
        return new ArrayList<>(rendaMap.values());
    }

    @Override
    public boolean update(Renda obj) {
        if(rendaMap.containsKey(obj.getId())){
            rendaMap.replace(obj.getId(),obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        if(rendaMap.containsKey(key)){
            rendaMap.remove(key);
            return true;

        }
        return false;

    }


    @Override
    public Optional<Renda> findById(Integer id) {
        if(rendaMap.containsKey(id)){
            return Optional.of(rendaMap.get(id));
        }
        return Optional.empty();
    }
}
