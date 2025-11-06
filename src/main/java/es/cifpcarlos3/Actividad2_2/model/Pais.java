package es.cifpcarlos3.Actividad2_2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pais {
    private int cod_continente;
    private int identificador;
    private String nombre_pais;
    private String capital;

    @Override
    public String toString() {
        return "Pais{Id= " + cod_continente + ", Identificador= " + identificador + ", Nombre= " + nombre_pais + ", Capital= " + capital + "}";
    }
}
