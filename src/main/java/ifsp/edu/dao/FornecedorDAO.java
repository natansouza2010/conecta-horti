package ifsp.edu.dao;

import ifsp.edu.model.Fornecedor;

import java.util.List;

public class FornecedorDAO implements DAO<Fornecedor, String>{
    @Override
    public boolean insert(Fornecedor obj) {
        return false;
    }

    @Override
    public Fornecedor findOne(String key) {
        return null;
    }

    @Override
    public List<Fornecedor> listAll() {
        return null;
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
