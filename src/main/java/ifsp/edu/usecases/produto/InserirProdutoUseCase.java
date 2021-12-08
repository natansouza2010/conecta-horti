package ifsp.edu.usecases.produto;

import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;
import ifsp.edu.sqlitedao.FornecedorDAOImpl;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class InserirProdutoUseCase {

    private ProdutoDAO dao;


    public InserirProdutoUseCase(ProdutoDAO dao) {
        this.dao = dao;
    }



    public boolean insert(Produto produto){
        FornecedorDAO daoFornecedor = new FornecedorDAOImpl();
        Validator<Produto> validator = new ProdutoValidator();
        Notification notification = validator.validate(produto);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        Integer id = produto.getId();
        if(dao.findById(id).isPresent()){
            throw new EntidadeExistenteException(notification.errorMessage());
        }

        String cnpj = produto.getFornecedor().getCnpj();
        if(daoFornecedor.findByCNPJ(cnpj).isEmpty()){
            throw new EntidadeNaoEncontradaException("CNPJ do fornecedor não encontrado.");
        }
        return dao.insert(produto);
    }
}
