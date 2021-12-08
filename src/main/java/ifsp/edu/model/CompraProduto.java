package ifsp.edu.model;

import java.time.LocalDate;

public class CompraProduto {
    private Produto produto;
    private LocalDate momento;
    private Double valor;
    private Integer id;

    public CompraProduto(Produto produto, LocalDate momento) {
        this.produto = produto;
        this.momento = momento;
        this.valor = produto.getValorCusto();
    }

    public CompraProduto(Integer id, Produto produto, LocalDate momento) {
        this.id = id;
        this.produto = produto;
        this.momento = momento;
        this.valor = produto.getValorCusto();
    }


    public Integer getId() {
        return id;
    }


    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor){
        this.valor = valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public LocalDate getMomento() {
        return momento;
    }

    public void setMomento(LocalDate momento) {
        this.momento = momento;
    }
}
