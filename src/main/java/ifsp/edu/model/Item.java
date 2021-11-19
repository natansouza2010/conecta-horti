package ifsp.edu.model;

public class Item {
//    private Integer id;
    private Double valorVenda;

    private Integer quantidade;
    private Produto produto;
    private Pedido pedido;

    public Item() {
    }

    public Item(Integer quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
//        this.id = produto.getId();
//        this.valorVenda = produto.getValorVenda();

    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public Pedido getPedido() {
        return pedido;
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
