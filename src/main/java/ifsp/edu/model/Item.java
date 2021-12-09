package ifsp.edu.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
//    private Integer id;
    private Double valorVenda;

    private Integer quantidade;
    private Produto produto;
    private Pedido pedido;

    public Item(Integer quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
//        this.id = produto.getId();
//        this.valorVenda = produto.getValorVenda();

    }


    public Double calculaValor(){
        return quantidade * produto.getValorVenda();

    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }
}
