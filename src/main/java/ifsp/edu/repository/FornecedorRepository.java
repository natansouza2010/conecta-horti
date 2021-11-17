package ifsp.edu.repository;

import ifsp.edu.dao.DAO;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;

import java.util.*;

public class FornecedorRepository implements FornecedorDAO {
    static Map<String, Fornecedor> fornecedorMap = new HashMap<>();

    @Override
    public boolean insert(Fornecedor obj) {
        fornecedorMap.put(obj.getCnpj(), obj);
        return true;
    }

    @Override
    public Fornecedor findOne(String key) {
        if(fornecedorMap.containsKey(key)){
            return fornecedorMap.get(key);
        }
        return null;
    }


    @Override
    public List<Fornecedor> listAll() {
        List<Fornecedor> fornecedorList = new ArrayList<>(fornecedorMap.values());
        return fornecedorList;
    }

    @Override
    public boolean update(Fornecedor obj) {
        if(fornecedorMap.containsKey(obj.getCnpj())){
            fornecedorMap.replace(obj.getCnpj(),obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String key) {
        if(fornecedorMap.containsKey(key)){
            fornecedorMap.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Fornecedor> findByCNPJ(String cnpj) {
        if(fornecedorMap.containsKey(cnpj)){
            return Optional.of(fornecedorMap.get(cnpj));
        }
        return Optional.empty();
    }
}