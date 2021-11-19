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

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private Integer id;
    private Cliente cliente;
    private  List<Item> items = new ArrayList<>();
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



    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public Integer getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Item> getItems() {
        return items;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public String getEndereco() {
        return endereco;
    }

    public FormaDePagamento getPagamento() {
        return pagamento;
    }

    public Double getValor() {
        return calculaTotalPedido();
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setPagamento(FormaDePagamento pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", items=" + items +
                ", valor=" + valor +
                ", dataPedido=" + dataPedido +
                ", status=" + status +
                ", endereco=" + endereco +
                ", pagamento=" + pagamento +
                '}';
    }
}
