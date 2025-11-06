package es.cifpcarlos3.Actividad2_2;

import es.cifpcarlos3.Actividad2_2.dao.ContinenteDAO;
import es.cifpcarlos3.Actividad2_2.dao.PaisDAO;
import es.cifpcarlos3.Actividad2_2.dao.impl.ContinenteDAOImpl;
import es.cifpcarlos3.Actividad2_2.dao.impl.PaisDAOImpl;

import java.util.Scanner;

public class App {

    private static final PaisDAO paisDao = new PaisDAOImpl();
    private static final ContinenteDAO continenteDao = new ContinenteDAOImpl();

    public static void main(String[] args) {
        Scanner sr = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Elige una opcion: ");
            System.out.println("1) Listar países sin capital");
            System.out.println("2) Nº de países por continente");
            System.out.println("3) Países de Europa");
            System.out.println("4) Capitales que empiezan por “San”");
            System.out.println("Opcion: ");
            opcion = sr.nextInt();
            switch (opcion) {
                case 1:
                    paisDao.listarPaisesSinCapital();
                    break;
                case 2:
                    continenteDao.paisPorContinente();
                    break;
                case 3:
                    paisDao.paisesEuropa();
                    break;
                case 4:
                    paisDao.listarCapitalesSan();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
            }

        }while (opcion != 5);
    }
}
