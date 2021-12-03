package ifsp.edu.enums;

import java.util.Arrays;

public enum StatusPedido {
    PAGO,
    A_PAGAR;

    public static StatusPedido toEnum(String value){
        return Arrays.stream(StatusPedido.values())
                .filter(f -> value.equals(f.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
