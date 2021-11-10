package ifsp.edu.model;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class Fornecedor {
    private String cnpj;
    private String nome;
    private String telefone1;
    private String telefone2;
    private String endereco;
    private String razaoSocial;
    private final List<Produto> produtos = new ArrayList<>();

    public Fornecedor(String cnpj, String nome) {
        this.cnpj = cnpj;
        this.nome = nome;
    }

    public Fornecedor(String cnpj, String nome, String telefone1, String telefone2, String endereco, String razaoSocial) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.endereco = endereco;
        this.razaoSocial = razaoSocial;
    }

    public String getNome() {
        return nome;
    }

    public void addProduto(Produto produto){
        produtos.add(produto);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }


}
