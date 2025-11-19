package es.cifpcarlos3.Actividad2_4.dao;

import es.cifpcarlos3.Actividad2_4.model.Cliente;

public interface ClienteDAO {
    void listarClientes();
    void crearCliente(Cliente cliente);
}
