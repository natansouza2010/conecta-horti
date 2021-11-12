package ifsp.edu.usecases.produto;

import ifsp.edu.model.Produto;
import ifsp.edu.utils.Notification;
import ifsp.edu.utils.Validator;

public class ProdutoValidator extends Validator <Produto> {

    @Override
    public Notification validate(Produto produto) {
        Notification notification = new Notification();

        if(produto == null){
            notification.addError("Produto é nulo");
            return notification;
        }
        if(nullOrEmpty(produto.getId().toString()))
            notification.addError("Id do produto é nulo ou vazio");

        if(nullOrEmpty(produto.getDescricao()))
            notification.addError("Descrição do produto é nula ou vazia");

        if(nullOrEmpty(produto.getNome()))
            notification.addError("Nome do produto é nulo ou vazio");

        if(nullOrEmpty(produto.getFornecedor().getCnpj()))
            notification.addError("Nome do fornecedor é nulo ou vazio");

        if(nullOrEmpty(produto.getValorVenda().toString()))
            notification.addError("Valor de venda do produto é nulo ou vazio");

        if(nullOrEmpty(produto.getValorCusto().toString()))
            notification.addError("Valor de custo do produto é nulo ou vazio");

        return notification;
    }
}
