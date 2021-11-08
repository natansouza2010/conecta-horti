package ifsp.edu.model;

public class Item {
    Integer quantidade;
    Produto produto;

    public Item(Integer quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Double calculaValor(){
        return quantidade * produto.getValorVenda();
    }
}
