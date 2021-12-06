package ifsp.edu.usecases.compraproduto;

import ifsp.edu.model.CompraProduto;
import ifsp.edu.model.Pedido;
import ifsp.edu.usecases.pedido.PedidoValidator;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class InsertCompraProdutoUseCase {
    private CompraProdutoDAO dao;

    public InsertCompraProdutoUseCase(CompraProdutoDAO dao) {
        this.dao = dao;
    }

    public boolean insert(CompraProduto compra){
        Validator<CompraProduto> validator = new CompraProdutoValidator();
        Notification notification = validator.validate(compra);

        if(notification.hasErrors()){
            throw new IllegalArgumentException(notification.errorMessage());
        }
        return dao.insert(compra);

    }
}
