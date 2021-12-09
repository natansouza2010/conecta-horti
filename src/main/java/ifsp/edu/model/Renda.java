package ifsp.edu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Data
public class Renda {
    private Integer id;
    private Double receita;
    private Double despesa;
    private Double lucroObtido;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private List<Pedido> pedidos;
    private List<CompraProduto> compras ;




    public Renda() {
    }



    public void calculaLucro(){
        Double valorPedidos = calculaValorPedidos(pedidos);
        Double valorCustos = calculaValorDespesa(compras);
        double lucro = valorPedidos - valorCustos;
        this.despesa = valorCustos;
        this.lucroObtido = lucro;
        this.receita = valorPedidos;
    }

    private Double calculaValorPedidos(List<Pedido> pedidos) {
        Double sum = 0.0;
        for (Pedido pedido : pedidos) {
            sum+= pedido.calculaTotalPedido();
        }
        return sum;
    }

    public Double calculaValorDespesa(List<CompraProduto> compras){
        Double sum = 0.0;
        for (CompraProduto compra : compras) {
            sum+= compra.getValor();
        }
        return sum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getReceita() {
        return receita;
    }

    public void setReceita(Double receita) {
        this.receita = receita;
    }

    public Double getDespesa() {
        return despesa;
    }

    public void setDespesa(Double despesa) {
        this.despesa = despesa;
    }

    public Double getLucroObtido() {
        return lucroObtido;
    }

    public void setLucroObtido(Double lucroObtido) {
        this.lucroObtido = lucroObtido;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<CompraProduto> getCompras() {
        return compras;
    }

    public void setCompras(List<CompraProduto> compras) {
        this.compras = compras;
    }

    @Override
    public String toString() {
        return "Renda{" +
                "id=" + id +
                ", despesa=" + despesa +
                ", lucroObtido=" + lucroObtido +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                ", pedidos=" + pedidos +
                '}';
    }
}
