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
                System.out.println("4) Capitales que empiezan por “San”\n");
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

            System.out.println("Paises sin capital:");
            while (resultado.next()) {
                System.out.println("- " + resultado.getString("nombre_pais"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void paisPorContinente() {
        String sql = "SELECT * FROM paises WHERE continente = ?";
    }

    private static void paisesEuropa() {
        String sql = "SELECT * FROM paises WHERE capital = ?";
    }

    private static void listarCapitalesSan() {
        String sql = "SELECT capital FROM T_PAIS WHERE capital LIKE 'San%'";
        try (var conexion = DriverManager.getConnection(url, user, pass);
             var sentencia = conexion.createStatement();
             var resultado = sentencia.executeQuery(sql)) {

            System.out.println("Capitales que empiezan por 'San':");
            while (resultado.next()) {
                System.out.println("- " + resultado.getString("capital"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
