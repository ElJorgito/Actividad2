package es.cifpcarlos3.Actividad2_5.dao;

public interface CuentaDAO {
    void listarCuentasInseguro(String dni, String password);
    void listarCuentasSeguro(String dni, String password);
}
