package ifsp.edu.enums;


import java.util.Arrays;

public enum FormaDePagamento {
    DINHEIRO,
    CARTAO;

    public static FormaDePagamento toEnum(String value){
        return Arrays.stream(FormaDePagamento.values())
                .filter(f -> value.equals(f.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }


}
