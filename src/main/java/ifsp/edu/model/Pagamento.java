package ifsp.edu.model;

import ifsp.edu.usecases.pagamento.PagamentoDAO;
import ifsp.edu.enums.FormaDePagamento;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Pagamento {
    private Integer id;
    private FormaDePagamento formaDePagamento;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void escolheFormaPagamento(){
        formaDePagamento = FormaDePagamento.CARTAO;
    }


    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", formaDePagamento=" + formaDePagamento +
                '}';
    }
}


