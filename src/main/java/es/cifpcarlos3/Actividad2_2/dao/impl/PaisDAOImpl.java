package es.cifpcarlos3.Actividad2_2.dao.impl;

import es.cifpcarlos3.Actividad2_2.dao.PaisDAO;
import es.cifpcarlos3.Actividad2_2.model.Pais;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PaisDAOImpl implements PaisDAO {
    private static final String url = "jdbc:mariadb://localhost:3306/mapa_mundi"; //MariaDB
    private static final String user = "root";
    private static final String pass = "";

    @Override
    public void listarPaisesSinCapital(){
        String sql = "SELECT nombre_pais FROM t_pais WHERE capital = null";
        try (var conexion = DriverManager.getConnection(url, user, pass);
             var sentencia = conexion.createStatement();
             var resultado = sentencia.executeQuery(sql)) {

            System.out.println("Paises sin capital: ");
            while (resultado.next()) {
                System.out.println("- " + resultado.getString("nombre_pais"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void paisesEuropa(){
        String sql = "SELECT p.nombre_pais FROM t_pais p JOIN t_continente c ON p.cod_continente = c.codigo WHERE c.nombre_continente = 'Europa';";
        try (var conexion = DriverManager.getConnection(url, user, pass);
             var sentencia = conexion.createStatement();
             var resultado = sentencia.executeQuery(sql)) {

            System.out.println("Países de Europa: ");
            while (resultado.next()) {
                System.out.println("- " + resultado.getString("p.nombre_pais"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void listarCapitalesSan(){
        String sql = "SELECT capital FROM T_PAIS WHERE capital LIKE 'San%'";
        try (var conexion = DriverManager.getConnection(url, user, pass);
             var sentencia = conexion.createStatement();
             var resultado = sentencia.executeQuery(sql)) {

            System.out.println("Capitales que empiezan por 'San': ");
            while (resultado.next()) {
                System.out.println("- " + resultado.getString("capital"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
