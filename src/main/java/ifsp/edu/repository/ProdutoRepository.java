package ifsp.edu.repository;

import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;
import ifsp.edu.usecases.produto.ProdutoDAO;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProdutoRepository implements ProdutoDAO {
    static Map<Integer,Produto> produtoMap = new HashMap<>();
    @Override
    public boolean insert(Produto obj) {
        produtoMap.put(obj.getId(),obj);
        return true;
    }

    @Override
    public Produto findOne(Integer key) {
        if(produtoMap.containsKey(key)){
            return produtoMap.get(key);
        }
        return null;
    }

    @Override
    public List<Produto> listAll() {
        return new ArrayList<>(produtoMap.values());
    }

    @Override
    public boolean update(Produto obj) {
        if(produtoMap.containsKey(obj.getId())){
            produtoMap.replace(obj.getId(),obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        if(produtoMap.containsKey(key)){
            produtoMap.remove(key);
            return true;

        }
        return false;

    }

    @Override
    public Optional<Produto> findById(Integer id) {
        if(produtoMap.containsKey(id))
            return Optional.of(produtoMap.get(id));
        return Optional.empty();
    }

    @Override
    public Produto findByNome(String nome) {
       List<Produto> array = new ArrayList<>(produtoMap.values());
       Produto produto =  array.stream().filter(a -> a.getNome() == nome).findFirst().get();
       return produto;
    }

    @Override
    public Optional<Produto> findByFornecedor(String cnpj) {
        /*List<Produto> array = new ArrayList<>(produtoMap.values());
        for (Produto produto : array) {
            if(produto.getFornecedor().getCnpj() == cnpj){
                return Optional.of(produto);
            }


        }*/
        return Optional.empty();

    }

    @Override
    public Fornecedor findFornecedorByProduto(Produto produto) {

        return null;
    }
}
