package ifsp.edu.usecases.compraproduto;

import ifsp.edu.model.Cliente;
import ifsp.edu.model.CompraProduto;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class CompraProdutoValidator extends Validator<CompraProduto> {

    @Override
    public Notification validate(CompraProduto compra) {
        Notification notification = new Notification();

        if(compra == null){
            notification.addError("Compra é nula");
            return notification;
        }

        return notification;
    }


}
