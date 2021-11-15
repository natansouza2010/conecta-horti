package ifsp.edu.usecases.fornecedor;

import ifsp.edu.model.Fornecedor;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class InsertFornecedorUseCase {
    private FornecedorDAO dao;

    public InsertFornecedorUseCase(FornecedorDAO dao) {
        this.dao = dao;
    }

    public boolean insert(Fornecedor fornecedor){
        Validator<Fornecedor> validator = new FornecedorValidator();
        Notification notification = validator.validate(fornecedor);

        if(notification.hasErrors()){
            throw new IllegalArgumentException(notification.errorMessage());
        }
        String cnpj = fornecedor.getCnpj();
        if(dao.findByCNPJ(cnpj).isPresent()){
            throw new EntidadeExistenteException("Cnpj já existente.");
        }
        return dao.insert(fornecedor);
    }
}
