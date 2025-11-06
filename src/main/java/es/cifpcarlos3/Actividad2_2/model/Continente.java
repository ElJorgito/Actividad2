package es.cifpcarlos3.Actividad2_2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Continente {
    private int codigo;
    private String nombre_continente;

    @Override
    public String toString() {
        return "Continente {Codigo= " + codigo + ", Nombre= " + nombre_continente + "}";
    }
}
