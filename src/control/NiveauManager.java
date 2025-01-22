package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("c434b8c5-f6cd-467e-9fdd-b099fbb0ae65")
public class NiveauManager {
    @objid ("a9c9098b-d8cb-4edd-8247-b7d3d57e8d11")
    private static int[][][] NIVEAUX; // Stocke les niveaux chargés

    @objid ("6cc2f1f9-ccb3-4d8e-8d14-2be1745924d3")
    private int niveauCourant = 0; // Niveau actuel

    @objid ("d29ad692-a43b-4c1f-b8c7-1caad01c2c3e")
    private static String cheminFichierNiveaux = "niveaux/niveaux.txt"; // Chemin du fichier des niveaux

    @objid ("7b72e7cb-5371-4ee3-8bec-bd83cee6b37d")
    public NiveauManager() {
        chargerNiveauxDepuisFichier(); // Charge les niveaux au démarrage
    }

    @objid ("2e663efd-5067-4e4d-b07f-9ca831df889f")
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

    @objid ("5a7b63d6-eabb-462e-93b1-dd83e0578adf")
    public int[][] getNiveauCourant() {
        if (NIVEAUX == null || niveauCourant >= NIVEAUX.length) {
            return new int[0][0]; // Retourne un tableau vide si aucun niveau n'est chargé
        }
        return NIVEAUX[niveauCourant]; // Retourne le niveau actuel
    }

    @objid ("8b14bc36-4b52-4e71-95d3-e26245015b9d")
    public boolean passerAuNiveauSuivant() {
        if (NIVEAUX == null || niveauCourant >= NIVEAUX.length - 1) {
            return false; // Dernier niveau atteint ou aucun niveau chargé
        }
        niveauCourant++; // Passe au niveau suivant
        return true;
    }

    @objid ("134617f2-67d5-4d24-9ce6-1f4fa8a9735a")
    public boolean estDernierNiveau() {
        return NIVEAUX == null || niveauCourant == NIVEAUX.length - 1; // Vérifie si c'est le dernier niveau
    }

    @objid ("4f44cc26-7b77-4e18-9781-94309366c734")
    public static void setCheminFichierNiveaux(String chemin) {
        cheminFichierNiveaux = chemin; // Permet de changer le chemin du fichier
    }

}
