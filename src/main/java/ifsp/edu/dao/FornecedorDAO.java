package ifsp.edu.dao;

import ifsp.edu.model.Fornecedor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FornecedorDAO implements DAO<Fornecedor, String>{
    static Map<String, Fornecedor> fornecedorMap = new HashMap<>();

    @Override
    public boolean insert(Fornecedor obj) {
        fornecedorMap.put(obj.getCnpj(), obj);
        return true;
    }

    @Override
    public Fornecedor findOne(String key) {
        return null;
    }

    @Override
    public List<Fornecedor> listAll() {
        List<Fornecedor> fornecedorList = new ArrayList<>(fornecedorMap.values());
        return fornecedorList;
    }

    @Override
    public boolean update(Fornecedor obj) {
        return false;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }
}
