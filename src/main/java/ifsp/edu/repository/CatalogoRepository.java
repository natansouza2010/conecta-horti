package ifsp.edu.repository;

import ifsp.edu.model.Catalogo;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Produto;
import ifsp.edu.usecases.catalogo.CatalogoDAO;

import java.time.LocalDate;
import java.util.*;

public class CatalogoRepository implements CatalogoDAO {
    static Map<Integer, Catalogo> catalogoMap = new HashMap<>();


    @Override
    public boolean insert(Catalogo obj) {
        catalogoMap.put(obj.getId(), obj);
        return true;
    }

    @Override
    public Catalogo findOne(Integer key) {
        if(catalogoMap.containsKey(key)){
            return catalogoMap.get(key);
        }
        return null;
    }

    @Override
    public List<Catalogo> listAll() {
        List<Catalogo> catalogoList = new ArrayList<>(catalogoMap.values());
        return catalogoList;
    }

    @Override
    public boolean update(Catalogo obj) {
        if(catalogoMap.containsKey(obj.getId())){
            catalogoMap.replace(obj.getId(),obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

//    public boolean updateProdutoDoCatalogo(Catalogo catalogo, Produto produto) {
//        for (Catalogo cat: catalogoMap.values()
//        ) {
//            if(cat == catalogo){
//                catalogo.setProduto(produto);
//                return true;
//            }
//        }
//        return false;
//    }


//    public boolean deleteProdutoDoCatalogo(LocalDate dataInicial, LocalDate dataFinal, Produto produto) {
//        for (Catalogo catalogo: catalogoMap.values()
//             ) {
//            if(catalogo.getDataInicial() == dataInicial && catalogo.getDataFinal() == dataFinal && catalogo.getProduto() == produto){
//                catalogo.setProduto(null);
//                return true;
//            }
//        }
//        return false;
//    }


    public Optional<LocalDate> findDataFinal(LocalDate dataFinal) {
        for (Catalogo catalogo : catalogoMap.values()
        ) {
            if (catalogo.getDataFinal() == dataFinal) {
                return Optional.of(dataFinal);
            }
        }
        return Optional.empty();
    }


    @Override
    public Optional<LocalDate> findDataInicial(LocalDate dataInicial) {
        for (Catalogo catalogo : catalogoMap.values()
        ) {
            if (catalogo.getDataFinal() == dataInicial) {
                return Optional.of(dataInicial);
            }
        }
        return Optional.empty();
    }


    @Override
    public Optional<Produto> findProdutoByName(String nome) {
        return Optional.empty();
    }
}
