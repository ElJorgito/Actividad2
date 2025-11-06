package es.cifpcarlos3.Actividad2_2.dao.impl;

import es.cifpcarlos3.Actividad2_2.dao.ContinenteDAO;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ContinenteDAOImpl implements ContinenteDAO {
    private static final String url = "jdbc:mariadb://localhost:3306/mapa_mundi"; //MariaDB
    private static final String user = "root";
    private static final String pass = "";

    @Override
    public void paisPorContinente() {
        String sql = "SELECT c.nombre_continente AS continente, COUNT(p.identificador) AS num_paises FROM t_continente c LEFT JOIN t_pais p ON c.codigo = p.cod_continente GROUP BY c.nombre_continente";
        try (var conexion = DriverManager.getConnection(url, user, pass);
             var sentencia = conexion.createStatement();
             var resultado = sentencia.executeQuery(sql)) {

            System.out.println("Número de países por continente: ");
            while (resultado.next()) {
                System.out.println(resultado.getString("continente") + ": " + resultado.getInt("num_paises"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
