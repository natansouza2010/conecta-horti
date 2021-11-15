package ifsp.edu.usecases.fornecedor;

import ifsp.edu.model.Fornecedor;

import java.util.Optional;

public class FindFornecedorUseCase {
    private FornecedorDAO dao;

    public FindFornecedorUseCase(FornecedorDAO dao) {
        this.dao = dao;
    }

    public Optional<Fornecedor> findOne(String cnpj){
        if(cnpj == null){
            throw new IllegalArgumentException("CNPJ não pode ser nulo.");
        }
        return dao.findByCNPJ(cnpj);
    }
}
