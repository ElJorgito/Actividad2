package es.cifpcarlos3.Actividad2_5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cuenta {
    private int idCuenta;
    private String numeroCuenta;
    private int idCliente;
    private double saldo;

    @Override
    public String toString() {return "Cuenta: idCuenta: " + idCuenta
            + " numeroCuenta: " + numeroCuenta + " idCliente: " + idCliente + " saldo: " + saldo;}
}
