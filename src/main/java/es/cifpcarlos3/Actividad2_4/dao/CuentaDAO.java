package es.cifpcarlos3.Actividad2_4.dao;

import es.cifpcarlos3.Actividad2_4.model.Cuenta;

public interface CuentaDAO {
    void listarCuentas();
    void actualizarSaldo(int idcuenta, double saldo);
    void insertarCuenta(Cuenta cuenta);
}
