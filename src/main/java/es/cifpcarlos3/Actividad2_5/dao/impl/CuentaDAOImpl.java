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
    public void listarCuentasSeguro(String dniCuenta, String password) {
        String sql = "SELECT c.id_cuenta, c.numero_cuenta, c.saldo, cli.dni " +
                "FROM T_CUENTA c " +
                "JOIN T_CLIENTE cli ON c.id_cliente = cli.id_cliente " +
                "JOIN T_USUARIO u ON u.dni = cli.dni " +
                "WHERE u.dni = ? AND u.password = ?; ";

        try (var conexion = db.getConn();
             var sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, dniCuenta);
            sentencia.setString(2, password);

            var resultado = sentencia.executeQuery();
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
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminarCuentaUsuario(String dniDelete, String pass, int idCuenta) {
        String sql = "DELETE c " +
                     "FROM T_CUENTA c " +
                     "JOIN T_CLIENTE cli ON c.id_cliente = cli.id_cliente " +
                     "JOIN T_USUARIO u ON u.dni = cli.dni " +
                     "WHERE u.dni = ? AND u.password = ? AND c.id_cuenta = ?;";
        try (var conexion = db.getConn();
             var sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, dniDelete);
            sentencia.setString(2, pass);
            sentencia.setInt(3, idCuenta);

            int filas = sentencia.executeUpdate();
            System.out.println("DNI: " + dniDelete);
            System.out.println("ID Cuenta: " + idCuenta);
            System.out.println("Cuenta eliminada correctamente. Filas afectadas: " +  filas);

        } catch (SQLException e) {
            System.out.println("No se eliminó ninguna cuenta (credenciales incorrectas o cuenta no pertenece a ese usuario)");
            System.out.println(e.getMessage());
        }
    }
}
