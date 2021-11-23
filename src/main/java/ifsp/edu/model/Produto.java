package ifsp.edu.model;


import java.util.List;

public class Produto {
    private String nome;
    private Integer id;
    private String descricao;
    private Double valorCusto;
    private Double valorVenda;
    private Fornecedor fornecedor;

    public Produto(String nome, Integer id, String descricao, Double valorCusto, Double valorVenda) {
        this.nome = nome;
        this.id = id;
        this.descricao = descricao;
        this.valorCusto = valorCusto;
        this.valorVenda = valorVenda;
    }


    public Produto(String nome, Integer id, String descricao, Double valorCusto, Double valorVenda, Fornecedor fornecedor) {
        this.nome = nome;
        this.id = id;
        this.descricao = descricao;
        this.valorCusto = valorCusto;
        this.valorVenda = valorVenda;
        this.fornecedor = fornecedor;

    }




    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(Double valorCusto) {
        this.valorCusto = valorCusto;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }




    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valorCusto=" + valorCusto +
                ", valorVenda=" + valorVenda +
                ", fornecedor=" + fornecedor +
                '}';
    }

}
