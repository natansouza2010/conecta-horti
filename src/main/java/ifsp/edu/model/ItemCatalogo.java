package ifsp.edu.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ItemCatalogo {
    private Catalogo catalogo;
    private Produto produto;
    private Double valor;

    public ItemCatalogo() {
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
