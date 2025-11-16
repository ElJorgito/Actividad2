package es.cifpcarlos3.Actividad2_4.dao.impl;

import es.cifpcarlos3.Actividad2_4.dao.ClienteDAO;
import es.cifpcarlos3.Actividad2_4.util.DatabaseConnection;

public class ClienteDAOImpl implements ClienteDAO {
    DatabaseConnection db  = new DatabaseConnection();
    @Override
    public void listarClientes() {
        String consulta = "select * from Cliente";
        try (var conexion = db.getConn();
             var sentencia = conexion.createStatement();
             var resultado = sentencia.executeQuery(consulta);) {
            while (resultado.next()) {

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
