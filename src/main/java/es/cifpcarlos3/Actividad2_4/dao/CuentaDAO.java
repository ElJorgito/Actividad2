package es.cifpcarlos3.Actividad2_4.dao;

public interface CuentaDAO {
    void listarCuentas();
    void actualizarSaldo(int idcuenta, double saldo);
}
