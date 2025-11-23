package es.cifpcarlos3.Actividad2_5.dao.impl;

import es.cifpcarlos3.Actividad2_5.dao.CuentaDAO;
import es.cifpcarlos3.Actividad2_5.util.DatabaseConnection;
import java.sql.SQLException;

public class CuentaDAOImpl implements CuentaDAO {
    DatabaseConnection db =  new DatabaseConnection();
    @Override
    public void listarCuentasInseguro(String dni, String password) {
        String sql = "SELECT c.id_cuenta, c.numero_cuenta, c.saldo " +
                "FROM T_CUENTA c " +
                "JOIN T_CLIENTE cli ON c.id_cliente = cli.id_cliente " +
                "JOIN T_USUARIO u ON u.dni = cli.dni " +
                "WHERE u.dni = '" + dni + "' " +
                "AND u.password = '" + password + "'";
        System.out.println("[DEBUG] SQL VULNERABLE: " + sql);

        try (var conexion = db.getConn();
             var sentencia = conexion.createStatement();
             var resultado = sentencia.executeQuery(sql)) {
            System.out.printf("%-4s %-10s %-20s %-10s%n",
                    "ID", "DNI", "Nº CUENTA", "SALDO");
            System.out.println("---- ---------- -------------------- ----------");
            int cont = 0;
            while (resultado.next()) {
                System.out.printf("%-4d %-10s %-20s %-10.2f%n",
                        resultado.getInt("id_cuenta"),
                        resultado.getString("dni"),
                        resultado.getString("numero_cuenta"),
                        resultado.getDouble("saldo"));
                cont++;
            }
            System.out.println("(" + cont + " cuentas)");
        } catch (SQLException e) {
            System.out.println("Error en consulta insegura: " + e.getMessage());
        }
    }

    @Override
    public void listarCuentasSeguro(String dni, String password) {
        String sql = "SELECT c.id_cuenta, c.numero_cuenta, c.saldo" +
                "FROM T_CUENTA c" +
                "JOIN T_CLIENTE cli ON c.id_cliente = cli.id_cliente" +
                "JOIN T_USUARIO u ON u.dni = cli.dni";

        try (var conexion = db.getConn();
             var sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, dni);
            sentencia.setString(2, password);

            try (var resultado = sentencia.executeQuery()) {
                System.out.printf("%-4s %-10s %-20s %-10s%n",
                        "ID", "DNI", "Nº CUENTA", "SALDO");
                System.out.println("---- ---------- -------------------- ----------");
                int cont = 0;
                while (resultado.next()) {
                    System.out.printf("%-4d %-10s %-20s %-10.2f%n",
                            resultado.getInt("id_cuenta"),
                            resultado.getString("dni"),
                            resultado.getString("numero_cuenta"),
                            resultado.getDouble("saldo"));
                    cont++;
                }
                System.out.println("(" + cont + " cuentas)");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
