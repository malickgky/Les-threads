//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // Créer un tableau d'entiers
        int[] tableau = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int nombreDeThreads = 3; // Nombre de threads dans le pool
        ExecutorService executor = Executors.newFixedThreadPool(nombreDeThreads);

        int taille = tableau.length;
        int taillePlage = taille / nombreDeThreads;
        Sommeur[] sommeurs = new Sommeur[nombreDeThreads];

        // Soumettre les tâches au pool de threads
        for (int i = 0; i < nombreDeThreads; i++) {
            int debut = i * taillePlage;
            int fin = (i == nombreDeThreads - 1) ? taille : (i + 1) * taillePlage; // Assurez-vous que le dernier thread prend tous les restes
            sommeurs[i] = new Sommeur(tableau, debut, fin);
            executor.submit(sommeurs[i]);
        }

        // Arrêter le pool de threads
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Attendre que tous les threads aient terminé
        }

        // Calculer la somme totale
        int sommeTotale = 0;
        for (Sommeur sommeur : sommeurs) {
            sommeTotale += sommeur.getSomme();
        }

        // Afficher la somme totale
        System.out.println("La somme totale du tableau est : " + sommeTotale);
    }
}