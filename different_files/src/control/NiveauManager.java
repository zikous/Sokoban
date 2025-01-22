package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NiveauManager {
    private static int[][][] NIVEAUX; // Stocke les niveaux chargés

    private int niveauCourant = 0; // Niveau actuel

    private static String cheminFichierNiveaux = "niveaux/niveaux.txt"; // Chemin du fichier des niveaux

    public NiveauManager() {
        chargerNiveauxDepuisFichier(); // Charge les niveaux au démarrage
    }

    private void chargerNiveauxDepuisFichier() {
        File file = new File(cheminFichierNiveaux);
        if (!file.exists()) {
            NIVEAUX = new int[0][0][0]; // Initialise un tableau vide si le fichier n'existe pas
            return;
        }
        
        List<int[][]> niveauxList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            List<int[]> currentLevel = new ArrayList<>();
        
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    if (!currentLevel.isEmpty()) {
                        niveauxList.add(currentLevel.toArray(new int[0][])); // Ajoute le niveau complet
                        currentLevel.clear(); // Réinitialise pour le prochain niveau
                    }
                } else {
                    String[] parts = line.trim().split(" ");
                    int[] row = new int[parts.length];
                    for (int i = 0; i < parts.length; i++) {
                        row[i] = Integer.parseInt(parts[i]); // Convertit la ligne en tableau d'entiers
                    }
                    currentLevel.add(row); // Ajoute la ligne au niveau actuel
                }
            }
        
            if (!currentLevel.isEmpty()) {
                niveauxList.add(currentLevel.toArray(new int[0][])); // Ajoute le dernier niveau
            }
        
            NIVEAUX = niveauxList.toArray(new int[0][0][0]); // Convertit la liste en tableau 3D
        } catch (IOException e) {
            NIVEAUX = new int[0][0][0]; // Initialise un tableau vide en cas d'erreur
        }
    }

    public int[][] getNiveauCourant() {
        if (NIVEAUX == null || niveauCourant >= NIVEAUX.length) {
            return new int[0][0]; // Retourne un tableau vide si aucun niveau n'est chargé
        }
        return NIVEAUX[niveauCourant]; // Retourne le niveau actuel
    }

    public boolean passerAuNiveauSuivant() {
        if (NIVEAUX == null || niveauCourant >= NIVEAUX.length - 1) {
            return false; // Dernier niveau atteint ou aucun niveau chargé
        }
        niveauCourant++; // Passe au niveau suivant
        return true;
    }

    public boolean estDernierNiveau() {
        return NIVEAUX == null || niveauCourant == NIVEAUX.length - 1; // Vérifie si c'est le dernier niveau
    }

    public static void setCheminFichierNiveaux(String chemin) {
        cheminFichierNiveaux = chemin; // Permet de changer le chemin du fichier
    }

}
