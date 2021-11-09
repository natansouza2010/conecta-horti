package ifsp.edu.model;

import ifsp.edu.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Pedido {
    private Integer id;
    private Cliente cliente;
    private  List<Item> items = new ArrayList<>();
    private Double valor;
    private LocalDate dataPedido;
    private StatusPedido status;
    private Endereco endereco;
    private Pagamento pagamento;

    public Pedido(Integer id, Cliente cliente, LocalDate dataPedido, StatusPedido status, Endereco endereco, Pagamento pagamento) {
        this.id = id;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.status = status;
        this.endereco = endereco;
        this.pagamento = pagamento;
    }

    public Pedido(Integer id, Cliente cliente, List<Item> items, LocalDate dataPedido, StatusPedido status, Endereco endereco, Pagamento pagamento) {
        this.id = id;
        this.cliente = cliente;
        this.items = items;
        this.valor = calculaTotalPedido();
        this.dataPedido = dataPedido;
        this.status = status;
        this.endereco = endereco;
        this.pagamento = pagamento;
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


    public Double getValor() {
        return calculaTotalPedido();
    }
}
