package es.cifpcarlos3.Actividad2_5;

import es.cifpcarlos3.Actividad2_5.dao.CuentaDAO;
import es.cifpcarlos3.Actividad2_5.dao.UsuarioDAO;
import es.cifpcarlos3.Actividad2_5.dao.impl.CuentaDAOImpl;
import es.cifpcarlos3.Actividad2_5.dao.impl.UsuarioDAOImpl;
import es.cifpcarlos3.Actividad2_5.util.DatabaseConnection;
import java.util.Scanner;

public class App {
    private static final DatabaseConnection db  = new DatabaseConnection();
    private static final CuentaDAO cuentaDAO = new CuentaDAOImpl();
    private static final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\nElige opción:");
            System.out.println("1) Crear tabla de usuarios");
            System.out.println("2) Importar usuarios a partir de clientes");
            System.out.println("3) Listar cuentas de un usuario con dni y password (SEGURO)");
            System.out.println("4) Listar cuentas de un usuario con dni y password (INSEGURO)");
            System.out.println("5) Eliminar cuenta de un usuario con dni, password e Id");
            System.out.println("6) Salir");
            System.out.print("\nOpción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    usuarioDAO.crearTablaUsuarios();
                    break;
                case 2:
                    usuarioDAO.importarUsuarios();
                    break;
                case 3:
                    System.out.println("DNI: ");
                    String Dni = sc.next();
                    System.out.println("Password: ");
                    String Password = sc.next();
                    cuentaDAO.listarCuentasSeguro(Dni, Password);
                    break;
                case 4:
                    System.out.println("DNI: ");
                    String dni = sc.next();
                    System.out.println("Password: ");
                    String password = sc.next();
                    cuentaDAO.listarCuentasInseguro(dni, password);
                    break;
                case 5:
                    System.out.print("DNI: ");
                    String dniDelete = sc.nextLine();
                    System.out.print("Password: ");
                    String pass = sc.nextLine();
                    System.out.print("ID de la cuenta a eliminar: ");
                    int idCuenta = sc.nextInt();
                    cuentaDAO.eliminarCuentaUsuario(dniDelete, pass, idCuenta);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        } while (opcion != 6);
    }
}
