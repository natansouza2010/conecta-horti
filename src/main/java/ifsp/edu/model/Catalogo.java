package ifsp.edu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Catalogo {
    private Integer id;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private List<Produto> produtos;

    @Override
    public String toString() {
        return "Catalogo{" +
                "id=" + id +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                ", produtos=" + produtos+
                '}';
    }
}
