package ifsp.edu.usecases.fornecedor;

import ifsp.edu.model.Fornecedor;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class UpdateFornecedorUseCase {
    private FornecedorDAO dao;

    public UpdateFornecedorUseCase(FornecedorDAO dao) {
        this.dao = dao;
    }

    public boolean update(Fornecedor fornecedor){
        Validator<Fornecedor> validator = new FornecedorValidator();
        Notification notification = validator.validate(fornecedor);

        if(notification.hasErrors()){
            throw new IllegalArgumentException(notification.errorMessage());
        }
        if(dao.findByCNPJ(fornecedor.getCnpj()).isEmpty()){
            throw new EntidadeNaoEncontradaException("Cnpj não existe");

        }
        return dao.update(fornecedor);

    }
}
