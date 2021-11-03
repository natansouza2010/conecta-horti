package ifsp.edu.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Produto {
    private String nome;
    private Integer id;
    private String descricao;
    private Double valorCusto;
    private Double valorVenda;
}
