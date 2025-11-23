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
}
