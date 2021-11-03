package ifsp.edu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone1;
    private String telefone2;

}
