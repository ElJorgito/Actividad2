package es.cifpcarlos3.Actividad2_4.dao.impl;

import es.cifpcarlos3.Actividad2_4.dao.ClienteDAO;
import es.cifpcarlos3.Actividad2_4.util.DatabaseConnection;

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
}
