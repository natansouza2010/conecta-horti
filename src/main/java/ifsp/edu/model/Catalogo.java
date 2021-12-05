package ifsp.edu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Catalogo {
    private Integer id;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Produto produto;

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
