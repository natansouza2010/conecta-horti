package ifsp.edu.model;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class Renda {
    private Integer id;
    private Double receita;
    private Double despesa;
    private Double lucroObtido;
    private LocalDate data;
}
