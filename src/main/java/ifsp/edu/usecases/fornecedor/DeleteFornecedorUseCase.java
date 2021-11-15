package ifsp.edu.usecases.fornecedor;

import ifsp.edu.model.Fornecedor;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class DeleteFornecedorUseCase {
    private FornecedorDAO dao;

    public DeleteFornecedorUseCase(FornecedorDAO dao){
        this.dao = dao;
    }

    public boolean delete(Fornecedor fornecedor){
        if(fornecedor == null || dao.findByCNPJ(fornecedor.getCnpj()).isEmpty())
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado");
        return dao.delete(fornecedor.getCnpj());
    }

    public boolean delete(String cnpj){
        if(cnpj.isEmpty() || dao.findByCNPJ(cnpj).isEmpty())
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado");
        return dao.delete(cnpj);
    }
}