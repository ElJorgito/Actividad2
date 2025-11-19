package es.cifpcarlos3.Actividad2_4.dao.impl;

import es.cifpcarlos3.Actividad2_4.dao.ClienteDAO;
import es.cifpcarlos3.Actividad2_4.model.Cliente;
import es.cifpcarlos3.Actividad2_4.util.DatabaseConnection;

import java.sql.SQLException;

public class ClienteDAOImpl implements ClienteDAO {
    DatabaseConnection db  = new DatabaseConnection();
    @Override
    public void listarClientes() {
        String consulta = "SELECT * FROM t_cliente ORDER BY id_cliente";
        try (var conexion = db.getConn();
             var sentencia = conexion.createStatement();
             var resultado = sentencia.executeQuery(consulta);) {
            System.out.printf("%-4s %-10s %-15s %-12s %-25s%n",
                    "ID", "DNI", "NOMBRE", "TELÉFONO", "EMAIL");
            System.out.println("---- ---------- --------------- ------------ -------------------------");
            int cont = 0;

            while (resultado.next()) {
                System.out.printf("%-4d %-10s %-15s %-12s %-25s%n",
                        resultado.getInt("id_cliente"),
                        resultado.getString("dni"),
                        resultado.getString("nombre"),
                        resultado.getString("telefono"),
                        resultado.getString("email"));
                cont++;
            }
            System.out.println();
            System.out.println("(" + cont + " clientes)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void crearCliente(Cliente cliente) {
        String sqlExiste = "SELECT * FROM t_cliente WHERE dni = ?";
        try (var conexion = db.getConn();
             var sentencia = conexion.prepareStatement(sqlExiste);){

            sentencia.setString(1, cliente.getDni());
            var resultado = sentencia.executeQuery();

            if (resultado.next()) {
                System.out.println("Este cliente ya esta en la base de datos");
            }else{
                String sql = "INSERT INTO t_cliente(id_cliente, dni, nombre, telefono, email) VALUES (?, ?, ?, ?, ?)";
                try(var conexion2 = db.getConn();
                    var sentencia2 = conexion2.prepareStatement(sql);) {

                    sentencia2.setInt(1, cliente.getIdCliente());
                    sentencia2.setString(2, cliente.getDni());
                    sentencia2.setString(3, cliente.getNombre());
                    sentencia2.setString(4, cliente.getTelefono());
                    sentencia2.setString(5, cliente.getEmail());

                    int filas = sentencia2.executeUpdate();
                    System.out.println("DNI: " + cliente.getDni());
                    System.out.println("Nombre: " + cliente.getNombre());
                    System.out.println("Telefono: " + cliente.getTelefono());
                    System.out.println("E-mail: " + cliente.getEmail());
                    System.out.println("Cliente insertado correctamente. Filas afectadas: " + filas);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("ID: " + cliente.getIdCliente());
            System.out.println("DNI: " + cliente.getDni());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Telefono: " + cliente.getTelefono());
            System.out.println("E-mail: " + cliente.getEmail());
            System.out.println("Este cliente ya esta en la base de datos" + e.getMessage());
        }
    }
}
