package ifsp.edu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fornecedor {
    private String cnpj;
    private String nome;
    private String telefone1;
    private String telefone2;
    private String endereco;
    private String razaoSocial;
}
