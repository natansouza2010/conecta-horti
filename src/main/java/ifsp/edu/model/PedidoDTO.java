package ifsp.edu.model;

import ifsp.edu.enums.FormaDePagamento;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PedidoDTO{
    private Cliente cliente;
    private Produto produto;
    private Integer quantidade;
    private FormaDePagamento formaDePagamento;
}
