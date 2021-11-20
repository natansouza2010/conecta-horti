package ifsp.edu.model;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Renda {
    private Integer id;
    private Double receita;
    private Double despesa;
    private Double lucroObtido;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private List<Pedido> pedidos = new ArrayList<>();
    private List<Produto> produtos  = new ArrayList<>();

////    public Renda(Integer id, LocalDate dataInicial) {
//        this.id = id;
//        this.dataInicial = dataInicial;
//    }

    public Renda(Integer id, LocalDate dataInicial, LocalDate dataFinal, List<Pedido> pedidos, List<Produto> produtos) {
        this.id = id;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.pedidos = pedidos;
        this.produtos = produtos;
        this.despesa = calculaValorDespesa();
        this.lucroObtido = calculaLucro();
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate data) {
        this.dataInicial = data;
    }

    public Double getReceita() {
        return receita;
    }

    public Double getDespesa() {
        return despesa;
    }

    public Double getLucroObtido() {
        return lucroObtido;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Double calculaLucro(){
        Double valorPedidos = calculaValorPedidos();
        Double valorCustos = calculaValorDespesa();
        double lucro = valorPedidos - valorCustos;
        return lucro;
    }

    private Double calculaValorPedidos() {
        Double sum = 0.0;
        for (Pedido pedido : pedidos) {
            sum+= pedido.calculaTotalPedido();
        }
        return sum;
    }

    public Double calculaValorDespesa(){
        Double sum = 0.0;
        for (Produto produto : produtos) {
            sum+= produto.getValorCusto();
        }
        return sum;
    }

    public void addPedido(Pedido pedido){
        pedidos.add(pedido);
    }

    public void addProduto(Produto produto){
        produtos.add(produto);
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
                ", produtos=" + produtos +
                '}';
    }
}
