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
    private LocalDate data;
    private List<Pedido> pedidos = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();

    public Renda(Integer id, LocalDate data) {
        this.id = id;
        this.data = data;
    }

    public Renda(Integer id, LocalDate data, List<Pedido> pedidos) {
        this.id = id;
        this.data = data;
        this.pedidos = pedidos;
    }

    public Double atualizarRenda(){
        Double valorPedidos = calculaValorPedidos();
        Double valorCustos = calculaValorDespesa();
        double lucro = valorPedidos - valorCustos;
        return this.lucroObtido = lucro;
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
        return this.despesa = sum;
    }

    public void addPedido(Pedido pedido){
        pedidos.add(pedido);
    }

    public void addProduto(Produto produto){
        produtos.add(produto);
    }
}
