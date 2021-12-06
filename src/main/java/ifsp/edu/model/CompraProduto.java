package ifsp.edu.model;

import java.time.LocalDate;

public class CompraProduto {
    private Produto produto;
    private LocalDate momento;


    public CompraProduto(Produto produto, LocalDate momento) {
        this.produto = produto;
        this.momento = momento;
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
