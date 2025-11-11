package es.cifpcarlos3.Actividad2_3;

import es.cifpcarlos3.Actividad2_3.dao.ContinenteDAO;
import es.cifpcarlos3.Actividad2_3.dao.PaisDAO;
import es.cifpcarlos3.Actividad2_3.dao.impl.ContinenteDAOImpl;
import es.cifpcarlos3.Actividad2_3.dao.impl.PaisDAOImpl;
import es.cifpcarlos3.Actividad2_3.util.DatabaseConnection;

import java.util.Scanner;

public class App {
    private static final DatabaseConnection db = new DatabaseConnection();
    private static final PaisDAO paisDao = new PaisDAOImpl(db);
    private static final ContinenteDAO continenteDao = new ContinenteDAOImpl(db);

    public static void main(String[] args) {
        Scanner sr = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Elige una opcion: ");
            System.out.println("1) Listar países del continente América con capital que empiece por 'Sa'");
            System.out.println("2) Insertar nuevo continente 'Antártida'");
            System.out.println("3) Actualizar capital del país con id 107 -> 'Capital city'");
            System.out.println("4) Eliminar continente con código '06'");
            System.out.println("Opcion: ");
            opcion = sr.nextInt();
            switch (opcion) {
                case 1:
                    paisDao.listarPaisesSa();
                    break;
                case 2:
                    continenteDao.insertarAntartida();
                    break;
                case 3:
                    paisDao.actualizarCapital();
                    break;
                case 4:
                    continenteDao.eliminarContinente();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
            }
        }while (opcion != 5);
    }
}
