package ifsp.edu.model;
import ifsp.edu.enums.FormaDePagamento;
import ifsp.edu.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pedido {
    private Integer id;
    private Cliente cliente;
    private List<Item> items ;
    private Double valor;
    private LocalDate dataPedido;
    private StatusPedido status;
    private String endereco;
    private FormaDePagamento pagamento;



    public Pedido(Integer id, Cliente cliente, LocalDate dataPedido, StatusPedido status, String endereco, FormaDePagamento pagamento) {
        this.id = id;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.status = status;
        this.endereco = endereco;
        this.pagamento = pagamento;
    }

    public Pedido(Integer id, Cliente cliente, List<Item> items, LocalDate dataPedido, StatusPedido status, String endereco, FormaDePagamento pagamento) {
        this.id = id;
        this.cliente = cliente;
        this.items = items;
        this.valor = calculaTotalPedido();
        this.dataPedido = dataPedido;
        this.status = status;
        this.endereco = endereco;
        this.pagamento = pagamento;
    }

    public Pedido(Cliente cliente, ArrayList<Item> listaItens, Double valorTotalPedido, LocalDate now, StatusPedido aPagar, String endereco, FormaDePagamento formaDePagamento) {
        this.cliente = cliente;
        this.items = items;
        this.valor = calculaTotalPedido();
        this.dataPedido = dataPedido;
        this.status = status;
        this.endereco = endereco;
        this.pagamento = pagamento;
    }

    public Pedido(List<Item> listaItens, LocalDate now, StatusPedido status, String endereco, FormaDePagamento formaDePagamento) {
        this.items = listaItens;
        this.dataPedido = now;
        this.status = status;
        this.endereco = endereco;
        this.pagamento = formaDePagamento;
    }

    public void addItems(Item item){
        items.add(item);
    }

    public Double calculaTotalPedido(){
        Double sum = 0.0;
        for (Item i : items) {
            sum += i.calculaValor();
        }
        return sum;
    }
}
