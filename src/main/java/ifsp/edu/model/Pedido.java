package ifsp.edu.model;

import ifsp.edu.enuns.StatusPedido;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class Pedido {
    private Integer id;
    private Double valor;
    private LocalDate dataPedido;
    private StatusPedido status;
    private String descricao;
}
