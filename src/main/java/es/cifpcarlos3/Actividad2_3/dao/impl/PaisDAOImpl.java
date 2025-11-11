package es.cifpcarlos3.Actividad2_3.dao.impl;

import es.cifpcarlos3.Actividad2_3.dao.PaisDAO;
import es.cifpcarlos3.Actividad2_3.util.DatabaseConnection;

import java.sql.DriverManager;

public class PaisDAOImpl implements PaisDAO {
    private final DatabaseConnection db;
    public PaisDAOImpl(DatabaseConnection db) {
        this.db = db;
    }
    @Override
    public void listarPaisesSa() {
        String sql = "SELECT p.nombre_pais FROM t_pais p JOIN t_continente c ON p.cod_continente = c.codigo WHERE c.nombre_continente = 'América' AND p.capital LIKE 'Sa%'";
        try (var conexion = db.getConn();
             var sentencia = conexion.createStatement();
             var resultado = sentencia.executeQuery(sql)){

            System.out.println("Paises que empiezan por Sa: ");
            while (resultado.next()) {
                System.out.println("- " + resultado.getString("p.capital") + " capital de "
                + resultado.getString("p.nombre_pais") + "(" + resultado.getString("p.cod_continente") + ")"
                + " pertenece al continente " + resultado.getString("c.nombre_continente")
                + "(" + resultado.getString("c.codigo") + ")");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
