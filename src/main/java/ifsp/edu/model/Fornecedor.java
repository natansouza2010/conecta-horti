package ifsp.edu.model;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class Fornecedor {
    private String cnpj;
    private String nome;
    private String telefone1;
    private String telefone2;
    private Endereco endereco;
    private String razaoSocial;

    public Fornecedor(String cnpj, String nome) {
        this.cnpj = cnpj;
        this.nome = nome;
    }

    public Fornecedor(String cnpj, String nome, String telefone1, String telefone2, Endereco endereco, String razaoSocial) {
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


    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }
}
