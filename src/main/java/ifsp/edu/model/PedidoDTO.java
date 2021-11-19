package ifsp.edu.model;

import ifsp.edu.enums.FormaDePagamento;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PedidoDTO{
    private Cliente cliente;
    private List<Item> items;
    private FormaDePagamento formaDePagamento;


    public Double calculaTotalPedido(){
        Double sum = 0.0;
        for (Item i : items) {
            sum += i.calculaValor();
        }
        return sum;
    }


}
