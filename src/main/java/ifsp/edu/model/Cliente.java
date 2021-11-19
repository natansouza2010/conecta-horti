package ifsp.edu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@Data
public class Cliente {
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone1;
    private String telefone2;


    public Cliente(String cpf, String nome, String endereco, String telefone1, String telefone2) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cpf.equals(cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
