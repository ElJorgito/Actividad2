package es.cifpcarlos3.Actividad2_4;

import es.cifpcarlos3.Actividad2_4.dao.ClienteDAO;
import es.cifpcarlos3.Actividad2_4.dao.CuentaDAO;
import es.cifpcarlos3.Actividad2_4.dao.impl.ClienteDAOImpl;
import es.cifpcarlos3.Actividad2_4.dao.impl.CuentaDAOImpl;
import es.cifpcarlos3.Actividad2_4.model.Cliente;
import es.cifpcarlos3.Actividad2_4.util.DatabaseConnection;
import java.util.Scanner;

public class App {
    private static final DatabaseConnection db  = new DatabaseConnection();
    private static final ClienteDAO clienteDAO = new ClienteDAOImpl();
    private static final CuentaDAO cuentaDAO = new CuentaDAOImpl();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\nElige opción:");
            System.out.println("1) Listar todos los clientes");
            System.out.println("2) Listar todas las cuentas con su titular");
            System.out.println("3) Insertar nuevo cliente");
            System.out.println("4) Insertar nueva cuenta para un cliente");
            System.out.println("5) Actualizar saldo de una cuenta");
            System.out.println("6) Transferir saldo entre dos cuenta");
            System.out.println("7) Eliminar cliente");
            System.out.println("8) Salir");
            System.out.print("\nOpción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    clienteDAO.listarClientes();
                    break;
                case 2:
                    cuentaDAO.listarCuentas();
                    break;
                case 3:
                    System.out.println("Id del cliente: ");
                    int id = sc.nextInt();
                    System.out.println("DNI del cliente: ");
                    String dni = sc.next();
                    System.out.println("Nombre del cliente: ");
                    String nombre = sc.next();
                    System.out.println("TLF del cliente: ");
                    String tlf = sc.next();
                    System.out.println("E-mail del cliente: ");
                    String email = sc.next();
                    Cliente cliente = new Cliente(id, dni, nombre, tlf, email);
                    clienteDAO.crearCliente(cliente);
                    break;
                case 4:
                    System.out.println("Insertar nueva cuenta para un cliente");
                    break;
                case 5:
                    System.out.println("Actualizar saldo de una cuenta");
                    break;
                case 6:
                    System.out.println("Transferir saldo entre dos cuentas");
                    break;
                case 7:
                    System.out.println("Eliminar cliente");
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        } while (opcion != 8);
    }
}
