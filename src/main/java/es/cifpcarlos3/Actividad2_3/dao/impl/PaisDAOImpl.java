package es.cifpcarlos3.Actividad2_3.dao.impl;

import es.cifpcarlos3.Actividad2_3.dao.PaisDAO;
import es.cifpcarlos3.Actividad2_3.util.DatabaseConnection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PaisDAOImpl implements PaisDAO {
    private final DatabaseConnection db;
    public PaisDAOImpl(DatabaseConnection db) {
        this.db = db;
    }
    @Override
    public void listarPaisesSa() {
        String sql = "SELECT p.identificador, p.nombre_pais, p.capital, c.codigo AS cod_continente, c.nombre_continente AS nombre_continente " +
                "FROM t_pais p JOIN t_continente c ON p.cod_continente = c.codigo WHERE c.nombre_continente = 'América' AND p.capital LIKE 'Sa%'";
        try (var conexion = db.getConn();
             var sentencia = conexion.createStatement();
             var resultado = sentencia.executeQuery(sql)){

            System.out.println("Paises que empiezan por Sa: ");
            while (resultado.next()) {
                System.out.println("- " +  resultado.getString("p.capital") + " capital de "
                + resultado.getString("p.nombre_pais") + "(" + resultado.getString("p.identificador") + ")"
                + " pertenece al continente " + resultado.getString("nombre_continente") + resultado.getString("cod_continente"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void actualizarCapital() {
        String sql = "UPDATE t_pais SET capital='Capital City' WHERE identificador=107";
        try(var conexion = db.getConn();
            var sentencia = conexion.createStatement();) {
            int filas = sentencia.executeUpdate(sql);
            System.out.println("Actualizado país id=107. Filas afectadas: " + filas);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
