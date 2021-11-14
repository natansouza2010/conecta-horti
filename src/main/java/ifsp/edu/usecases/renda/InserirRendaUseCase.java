package ifsp.edu.usecases.renda;

import ifsp.edu.model.Renda;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class InserirRendaUseCase {

    private RendaDAO dao;

    public InserirRendaUseCase(RendaDAO dao) {
        this.dao = dao;
    }

    public boolean insert(Renda renda){
        Validator<Renda> validator = new RendaValidator();
        Notification notification = validator.validate(renda);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        Integer id = renda.getId();
        if(dao.findById(id).isPresent()){
            throw new EntidadeExistenteException(notification.errorMessage());
        }

        return dao.insert(renda);
    }
}
