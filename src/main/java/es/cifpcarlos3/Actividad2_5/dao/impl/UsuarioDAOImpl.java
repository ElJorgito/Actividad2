package es.cifpcarlos3.Actividad2_5.dao.impl;

import es.cifpcarlos3.Actividad2_5.dao.UsuarioDAO;
import es.cifpcarlos3.Actividad2_5.util.DatabaseConnection;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {
    DatabaseConnection db = new DatabaseConnection();
    @Override
    public void crearTablaUsuarios() {
        String sql = "CREATE TABLE IF NOT EXISTS T_USUARIO (" +
                " dni VARCHAR(9) PRIMARY KEY," +
                " password VARCHAR(100) NOT NULL," +
                " CONSTRAINT fk_usuario_cliente" +
                " FOREIGN KEY (dni) REFERENCES T_CLIENTE(dni)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

        try (var conexion = db.getConn();
             var sentencia = conexion.prepareStatement(sql)) {
            sentencia.execute();
            System.out.println("Tabla T_USUARIO creada correctamente (o ya existía).");

        } catch (SQLException e) {
            System.out.println("No se pudo crear la tabla T_USUARIO.");
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void importarUsuarios() {

        String sqlClientes = "SELECT dni FROM T_CLIENTE";
        String sqlCheck = "SELECT dni FROM T_USUARIO WHERE dni = ?";
        String sqlInsert = "INSERT INTO T_USUARIO(dni, password) VALUES (?, ?)";

        int importados = 0;

        try (var conexion = db.getConn();
             var sentenciaClientes = conexion.prepareStatement(sqlClientes);
             var resultadoClientes = sentenciaClientes.executeQuery()) {

            while (resultadoClientes.next()) {
                String dni = resultadoClientes.getString("dni");

                try (var conexion2 = db.getConn();
                     var sentenciaCheck = conexion2.prepareStatement(sqlCheck)) {
                    sentenciaCheck.setString(1, dni);
                    var resultadoCheck = sentenciaCheck.executeQuery();

                    if (!resultadoCheck.next()) {
                        try (var conexion3 = db.getConn();
                             var sentenciaInsert = conexion3.prepareStatement(sqlInsert)) {
                            sentenciaInsert.setString(1, dni);
                            sentenciaInsert.setString(2, "1234");

                            int filas = sentenciaInsert.executeUpdate();
                            if (filas == 1) {
                                importados++;
                            }
                        } catch (SQLException e) {
                            System.out.println("No se pudo insertar usuario:");
                            System.out.println(e.getMessage());
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("No se pudo comprobar existencia:");
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Se han importado" + importados + "usuarios nuevos a T_USUARIO");
        } catch (SQLException e) {
            System.out.println("No se pudo obtener la lista de clientes:");
            System.out.println(e.getMessage());
        }
    }
}
