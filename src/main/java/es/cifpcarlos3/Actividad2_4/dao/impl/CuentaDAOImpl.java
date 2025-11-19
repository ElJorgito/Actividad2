package es.cifpcarlos3.Actividad2_4.dao.impl;

import es.cifpcarlos3.Actividad2_4.dao.CuentaDAO;
import es.cifpcarlos3.Actividad2_4.util.DatabaseConnection;

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
        String sqlExiste = "SELECT id_cuenta FROM T_CUENTA WHERE id_cuenta = ?";
        try (var conexion = db.getConn();
             var sentencia = conexion.prepareStatement(sqlExiste);){

            sentencia.setInt(1, idcuenta);
            var resultado = sentencia.executeQuery();

            if (!resultado.next()) {
                System.out.println("ERROR: No existe ninguna cuenta con ese ID.");
            }else{
                String sql = "UPDATE T_CUENTA SET saldo = ? WHERE id_cuenta = ?";
                try(var conexion2 = db.getConn();
                    var sentencia2 = conexion2.prepareStatement(sql);) {
                    sentencia2.setDouble(1, saldo);
                    sentencia2.setInt(2, idcuenta);

                    int filas = sentencia2.executeUpdate();
                    System.out.println("ID de la cuenta: " + idcuenta);
                    System.out.println("Saldo de la cuenta: " + saldo);
                    System.out.println("Saldo actualizado. Filas afectadas: " + filas);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("ID de la cuenta: " + idcuenta);
            System.out.println("Saldo de la cuenta: " + saldo);
            System.out.println("No se actualizó ninguna cuenta (id no encontrado)");
        }
    }
}
