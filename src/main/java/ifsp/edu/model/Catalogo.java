package ifsp.edu.model;

import java.time.LocalDate;

public class Catalogo {

    private Integer id;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Produto produto;

    public Catalogo(Integer id, LocalDate dataInicial, LocalDate dataFinal, Produto produto) {
        this.id = id;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.produto = produto;
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

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "id=" + id +
                "dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                ", produto=" + produto +
                '}';
    }
}
