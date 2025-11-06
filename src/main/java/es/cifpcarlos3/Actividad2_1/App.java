package es.cifpcarlos3.Actividad2_1;

import java.sql.*;
import java.util.Scanner;

public class App {
    private static final String url = "jdbc:mariadb://localhost:3306/mapa_mundi"; //MariaDB
    private static final String user = "root";
    private static final String pass = "";

    public static void main(String[] args) {
        try ( var conexion = DriverManager.getConnection(url, user, pass) ) {
            System.out.println("Conexión exitosa a la base de datos.");
            Scanner sc = new Scanner(System.in);
            int opcion;
            do {
                System.out.println("Elige una opcion: ");
                System.out.println("1) Listar países sin capital");
                System.out.println("2) Nº de países por continente");
                System.out.println("3) Países de Europa");
                System.out.println("4) Capitales que empiezan por “San”");
                System.out.println("Opcion: ");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        listarPaisesSinCapital();
                        break;
                    case 2:
                        paisPorContinente();
                        break;
                    case 3:
                        paisesEuropa();
                        break;
                    case 4:
                        listarCapitalesSan();
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                }

            }while (opcion != 5);

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    private static void listarPaisesSinCapital() {
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

    private static void paisPorContinente() {
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

    private static void paisesEuropa() {
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

    private static void listarCapitalesSan() {
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
