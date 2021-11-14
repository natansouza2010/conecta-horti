package ifsp.edu.usecases.renda;

import ifsp.edu.model.Renda;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class RendaValidator extends Validator<Renda> {
    @Override
    public Notification validate(Renda renda) {
        Notification notification = new Notification();

        if(renda == null){
            notification.addError("Renda é nula");
            return notification;
        }
        if(nullOrEmpty(renda.getId().toString()))
            notification.addError("Id da renda é nula ou vazia");

        if(nullOrEmpty(renda.getDataInicial().toString()))
            notification.addError("Data inicial da renda é nula ou vazia");

        return notification;
    }
}
