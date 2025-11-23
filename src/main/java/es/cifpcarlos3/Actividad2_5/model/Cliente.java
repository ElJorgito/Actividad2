package es.cifpcarlos3.Actividad2_5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private int idCliente;
    private String dni;
    private String nombre;
    private String telefono;
    private String email;

    @Override
    public String toString() {return "{Cliente: idCliente: " + idCliente + " nombre: " + nombre
            + " telefono: " + telefono + " email: " + email + "}";}
}
