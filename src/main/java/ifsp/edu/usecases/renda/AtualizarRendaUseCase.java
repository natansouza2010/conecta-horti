package ifsp.edu.usecases.renda;

import ifsp.edu.model.Renda;
import ifsp.edu.utils.EntidadeExistenteException;
import ifsp.edu.utils.EntidadeNaoEncontradaException;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class AtualizarRendaUseCase {

    private RendaDAO dao;

    public AtualizarRendaUseCase(RendaDAO dao) {
        this.dao = dao;
    }

    public boolean update(Renda renda){
        Validator<Renda> validator = new RendaValidator();
        Notification notification = validator.validate(renda);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        Integer id = renda.getId();
        if(dao.findById(id).isEmpty()){
            throw new EntidadeNaoEncontradaException("Essa renda não está cadastrada");
        }

        return dao.update(renda);
    }
}
