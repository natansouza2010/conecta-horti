package ifsp.edu.usecases.catalogo;

import ifsp.edu.model.Catalogo;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class CatalogoValidator extends Validator<Catalogo> {

    @Override
    public Notification validate(Catalogo catalogo) {
        Notification notification = new Notification();

        if(catalogo == null){
            notification.addError("Catalogo é nulo");
            return notification;
        }
        if(nullOrEmpty(catalogo.getDataInicial().toString()))
            notification.addError("Data inicial do catálogo é nula ou vazia");

        if(nullOrEmpty(catalogo.getDataFinal().toString()))
            notification.addError("Data final do catálogo é nula ou vazia");




        return notification;
    }
}
