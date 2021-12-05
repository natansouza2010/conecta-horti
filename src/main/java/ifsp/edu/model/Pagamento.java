package ifsp.edu.model;

import ifsp.edu.usecases.pagamento.PagamentoDAO;
import ifsp.edu.enums.FormaDePagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Pagamento {
    private Integer id;
    private FormaDePagamento formaDePagamento;

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void escolheFormaPagamento(){
        formaDePagamento = FormaDePagamento.CARTAO;
    }
}


