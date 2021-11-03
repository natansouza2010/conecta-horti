package ifsp.edu.model;

import ifsp.edu.enuns.FormaDePagamento;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Pagamento {
    private Integer id;
    private FormaDePagamento formaDePagamento;
}
