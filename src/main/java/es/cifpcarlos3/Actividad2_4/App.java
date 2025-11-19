package es.cifpcarlos3.Actividad2_4;

import es.cifpcarlos3.Actividad2_4.dao.ClienteDAO;
import es.cifpcarlos3.Actividad2_4.dao.CuentaDAO;
import es.cifpcarlos3.Actividad2_4.dao.impl.ClienteDAOImpl;
import es.cifpcarlos3.Actividad2_4.dao.impl.CuentaDAOImpl;
import es.cifpcarlos3.Actividad2_4.model.Cliente;
import es.cifpcarlos3.Actividad2_4.model.Cuenta;
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
                    System.out.print("ID de la cuenta: ");
                    int idCuenta = Integer.parseInt(sc.nextLine());
                    System.out.print("Número de cuenta: ");
                    String numeroCuenta = sc.nextLine();
                    System.out.print("ID del cliente al que pertenece: ");
                    int idCliente = Integer.parseInt(sc.nextLine());
                    System.out.print("Saldo inicial: ");
                    double saldo = sc.nextDouble();
                    Cuenta cuenta = new Cuenta(idCuenta, numeroCuenta, idCliente, saldo);

                    break;
                case 5:
                    System.out.println("Introduce id de la cuenta: ");
                    int idcuenta = sc.nextInt();
                    System.out.println("Introduce el saldo de la cuenta: ");
                    double saldonuevo = sc.nextDouble();
                    cuentaDAO.actualizarSaldo(idcuenta, saldonuevo);
                    break;
                case 6:
                    System.out.println("Transferir saldo entre dos cuentas");
                    break;
                case 7:
                    System.out.println("Id del cliente a eliminar: ");
                    int clienteid = sc.nextInt();
                    clienteDAO.eliminarCliente(clienteid);
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
