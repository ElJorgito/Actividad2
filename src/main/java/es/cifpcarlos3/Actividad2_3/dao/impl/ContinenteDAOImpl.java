package es.cifpcarlos3.Actividad2_3.dao.impl;

import es.cifpcarlos3.Actividad2_3.dao.ContinenteDAO;
import es.cifpcarlos3.Actividad2_3.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class ContinenteDAOImpl implements ContinenteDAO {
    private final DatabaseConnection db;
    public ContinenteDAOImpl(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public void insertarAntartida() {
        String sql = "INSERT INTO t_continente(codigo, nombre_continente) VALUES(06, 'Antártida')";
        try (var conexion = db.getConn();
             var sentencia = conexion.createStatement();){
            int filas = sentencia.executeUpdate(sql);
            System.out.println("Insertado continente Antártida (06). Filas afectadas: " + filas);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
