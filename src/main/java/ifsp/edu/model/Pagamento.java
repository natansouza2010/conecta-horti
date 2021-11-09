package ifsp.edu.model;

import ifsp.edu.enums.FormaDePagamento;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Pagamento {
    private Integer id;
    private FormaDePagamento formaDePagamento;
}
