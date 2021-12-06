package ifsp.edu.repository;

import ifsp.edu.model.CompraProduto;
import ifsp.edu.model.Pedido;
import ifsp.edu.usecases.compraproduto.CompraProdutoDAO;
import ifsp.edu.usecases.produto.ProdutoDAO;

import java.util.*;

public class CompraProdutoRepository implements CompraProdutoDAO {
    static Map<Integer, CompraProduto> comprasMap = new HashMap<>();

    @Override
    public boolean insert(CompraProduto obj) {
        comprasMap.put(obj.getId(), obj);
        return true;
    }

    @Override
    public CompraProduto findOne(Integer key) {
        if(comprasMap.containsKey(key)){
            return comprasMap.get(key);
        }
        return null;
    }

    @Override
    public List<CompraProduto> listAll() {
        List<CompraProduto> comprasList = new ArrayList<>(comprasMap.values());
        return comprasList;
    }

    @Override
    public boolean update(CompraProduto obj) {
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        if(comprasMap.containsKey(key)){
            comprasMap.remove(key);
            return true;
        }

        return false;
    }



    @Override
    public Optional<CompraProduto> findByCompra(CompraProduto compra) {
        if (comprasMap.containsValue(compra)) {
            return Optional.of(compra);
        }
        return Optional.empty();
    }
}
