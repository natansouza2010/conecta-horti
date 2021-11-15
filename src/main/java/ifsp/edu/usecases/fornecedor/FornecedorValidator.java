package ifsp.edu.usecases.fornecedor;

import ifsp.edu.model.Fornecedor;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class FornecedorValidator extends Validator<Fornecedor> {
    @Override
    public Notification validate(Fornecedor fornecedor) {
        Notification notification = new Notification();

        if(fornecedor == null){
            notification.addError("Fornecedor é nulo");
            return notification;
        }
        if(nullOrEmpty(fornecedor.getCnpj()))
            notification.addError("CNPJ do fornecedor é nulo ou vazio");

        if(nullOrEmpty(fornecedor.getNome()))
            notification.addError("Nome do fornecedor é nulo ou vazio");

        return notification;
    }
}
