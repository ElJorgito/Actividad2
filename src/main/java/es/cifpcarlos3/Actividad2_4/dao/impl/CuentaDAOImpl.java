package es.cifpcarlos3.Actividad2_4.dao.impl;

import es.cifpcarlos3.Actividad2_4.dao.CuentaDAO;
import es.cifpcarlos3.Actividad2_4.model.Cuenta;
import es.cifpcarlos3.Actividad2_4.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CuentaDAOImpl implements CuentaDAO {
    DatabaseConnection db = new DatabaseConnection();
    @Override
    public void listarCuentas() {
        String consulta = "SELECT * FROM t_cuenta ORDER BY id_cuenta";
        try(var conexion = db.getConn();
            var sentencia = conexion.createStatement();
            var resultado = sentencia.executeQuery(consulta);){
            System.out.printf("%-4s %-20s %-12s %-10s%n",
                    "ID", "Nº CUENTA", "TITULAR", "SALDO");
            System.out.println("---- ------------------- ------------ ---------");
            int cont = 0;
            while (resultado.next()) {
                System.out.printf("%-4s %-20s %-12s %-10.2f%n",
                        resultado.getString("id_cuenta"),
                        resultado.getString("numero_cuenta"),
                        resultado.getString("id_cliente"),
                        resultado.getDouble("saldo"));
                cont++;
            }
            System.out.println();
            System.out.println("(" + cont + " cuentas)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizarSaldo(int idcuenta, double saldo) {
        String sqlExiste = "SELECT id_cuenta FROM t_cuenta WHERE id_cuenta = ?";
        try(var conexion = db.getConn();
            var sentencia = conexion.prepareStatement(sqlExiste);){

            sentencia.setInt(1, idcuenta);
            var resultado = sentencia.executeQuery();

            if (!resultado.next()) {
                System.out.println("No se actualizo ninguna cuenta id no encontrado");
            } else {
                String sql = "UPDATE t_cuenta SET saldo = ? WHERE id_cuenta = ?";
                try (var conexion2 = db.getConn();
                     var sentencia2 = conexion2.prepareStatement(sql);) {
                    sentencia2.setDouble(1, saldo);
                    sentencia2.setInt(2, idcuenta);
                    int filas =  sentencia2.executeUpdate();
                    System.out.println("ID de la cuenta: " + idcuenta);
                    System.out.println("Saldo de la cuenta: " + saldo);
                    System.out.println("Saldo actualizado. Filas afectadas: " + filas);

                }  catch (SQLException e) {
                    System.out.println("ID de la cuenta: " + idcuenta);
                    System.out.println("Saldo de la cuenta: " + saldo);
                    System.out.println("No se actualizo ninguna cuenta id no encontrado");
                }
            }
        } catch (SQLException e) {
            System.out.println("ID de la cuenta: " + idcuenta);
            System.out.println("Saldo de la cuenta: " + saldo);
            System.out.println("No se actualizo ninguna cuenta id no encontrado");
        }
    }

    @Override
    public void insertarCuenta(Cuenta cuenta) {
        String sqlCliente = "SELECT id_cliente FROM t_cliente WHERE id_cliente = ?";
        try (var conexion = db.getConn();
             var sentencia = conexion.prepareStatement(sqlCliente)) {

            sentencia.setInt(1, cuenta.getIdCliente());
            var resultado = sentencia.executeQuery();

            if (!resultado.next()) {
                System.out.println("Error: el cliente " + cuenta.getIdCliente() + " no existe. No se creó la cuenta.");
            } else {
                String sqlExiste = "SELECT id_cuenta FROM t_cuenta WHERE id_cuenta = ?";

                try (var conexion2 = db.getConn();
                     var sentencia2 = conexion2.prepareStatement(sqlExiste)) {
                    sentencia2.setInt(1, cuenta.getIdCuenta());
                    var resultado2 = sentencia2.executeQuery();

                    if (resultado2.next()) {
                        System.out.println("Error: la cuenta con ID " + cuenta.getIdCuenta() + " ya existe. No se creó la cuenta.");
                    } else {
                        String sqlInsert = "INSERT INTO t_cuenta(id_cuenta, numero_cuenta, id_cliente, saldo) VALUES (?, ?, ?, ?)";

                        try (var conexion3 = db.getConn();
                             var sentencia3 = conexion3.prepareStatement(sqlInsert)) {
                            sentencia3.setInt(1, cuenta.getIdCuenta());
                            sentencia3.setString(2, cuenta.getNumeroCuenta());
                            sentencia3.setInt(3, cuenta.getIdCliente());
                            sentencia3.setDouble(4, cuenta.getSaldo());

                            int filas = sentencia3.executeUpdate();
                            System.out.println("ID del cliente: " + cuenta.getIdCliente());
                            System.out.println("Numero de la cuenta: " + cuenta.getNumeroCuenta());
                            System.out.println("Saldo inicial: " + cuenta.getSaldo());
                            System.out.println("Cuenta insertada correctamente. Filas afectadas: " + filas);
                        } catch (SQLException e) {
                            System.out.println("ID del cliente: " + cuenta.getIdCliente());
                            System.out.println("Numero de la cuenta: " + cuenta.getNumeroCuenta());
                            System.out.println("Saldo inicial: " + cuenta.getSaldo());
                            System.out.println("Error al insertar la cuenta: " + e.getMessage());
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("ID del cliente: " + cuenta.getIdCliente());
                    System.out.println("Numero de la cuenta: " + cuenta.getNumeroCuenta());
                    System.out.println("Saldo inicial: " + cuenta.getSaldo());
                    System.out.println("Error: la cuenta con ID " + cuenta.getIdCuenta() + " ya existe. No se creó la cuenta.");
                }
            }
        } catch (SQLException e) {
            System.out.println("ID del cliente: " + cuenta.getIdCliente());
            System.out.println("Numero de la cuenta: " + cuenta.getNumeroCuenta());
            System.out.println("Saldo inicial: " + cuenta.getSaldo());
            System.out.println("Error: el cliente " + cuenta.getIdCliente() + " no existe. No se creó la cuenta.");
        }
    }

    @Override
    public void transferencia(int cOrigen, int cDestino, double importe) {
        try(var conexion = db.getConn();){

            conexion.setAutoCommit(false);

            String sqlSaldo = "SELECT saldo FROM t_cuenta WHERE id_cuenta = ?";
            try(var sentencia =  conexion.prepareStatement(sqlSaldo)){
                sentencia.setDouble(1, importe);
                var resultado = sentencia.executeQuery();
                if (!resultado.next()) {
                    System.out.println("Transferencia NO realizada (saldo insuficiente o cuenta inexistente)");
                    conexion.rollback();
                    conexion.setAutoCommit(true);
                    return;
                }
            } catch (SQLException e) {
                System.out.println("Transferencia NO realizada (saldo insuficiente o cuenta inexistente");
                conexion.rollback();
                conexion.setAutoCommit(true);
                return;
            }

            String sqlExisteDestino = "SELECT id_cuenta FROM t_cuenta WHERE id_cuenta = ?";
            try (var sentencia2 = conexion.prepareStatement(sqlExisteDestino)) {

                sentencia2.setInt(1, cDestino);
                var resultado2 = sentencia2.executeQuery();

                if (!resultado2.next()) {
                    System.out.println("Transferencia NO realizada (saldo insuficiente o cuenta inexistente)");
                    conexion.rollback();
                    conexion.setAutoCommit(true);
                    return;
                }
            } catch (SQLException e) {
                System.out.println("Transferencia NO realizada (saldo insuficiente o cuenta inexistente)");
                conexion.rollback();
                conexion.setAutoCommit(true);
                return;
            }

            String sqlOrigen = "UPDATE t_cuenta SET saldo = saldo - ? WHERE id_cuenta = ?";
            try (var sentencia3 = conexion.prepareStatement(sqlExisteDestino)) {
                sentencia3.setDouble(1, importe);
                sentencia3.setInt(2, cOrigen);
                int filas = sentencia3.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Transferencia NO realizada (saldo insuficiente o cuenta inexistente)");
                conexion.rollback();
                conexion.setAutoCommit(true);
                return;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
