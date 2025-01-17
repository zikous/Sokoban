package control;

import java.util.Scanner;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import entity.*;
import entity.Direction;

@objid ("ec84e489-bafb-4824-8cf4-41ae8cb5eb6a")
public class TestSokoban {
    // Affiche le niveau sous forme de grille avec les symboles correspondants
    @objid ("73e38ded-55b6-424b-9e64-e554fbec53a4")
    private static void afficherNiveau(Controleur controleur, int lignes, int colonnes) {
        Entrepot entrepot = controleur.getEntrepot();
        Gardien gardien = controleur.getGardien();
        
        System.out.print("  ");
        for (int j = 0; j < colonnes; j++) {
            System.out.print(j % 10 + " ");
        }
        System.out.println();
        
        for (int i = 0; i < lignes; i++) {
            System.out.print(i % 10 + " ");
        
            for (int j = 0; j < colonnes; j++) {
                Position pos = entrepot.getPosition(i, j);
                Zone zone = pos.getZone();
        
                char symbole = ' ';
                // Choix du symbole selon le type de zone
                if (zone.isEstMur()) {
                    symbole = '#';  // Mur
                } else if (zone == gardien.getZone()) {
                    symbole = '@';  // Gardien
                } else if (zone.contientCaisse()) {
                    symbole = zone.isEstCible() ? '*' : '$'; // Caisse ou caisse sur cible
                } else if (zone.isEstCible()) {
                    symbole = '.';  // Cible
                }
                System.out.print(symbole + " ");
            }
            System.out.println();
        }
    }

    // Méthode principale pour lancer le jeu
    @objid ("7fadbec0-3ad7-45dc-a85e-7abaa8511f72")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controleur controleur = new Controleur();
        
        // Définition du niveau initial
        int[][] niveau = {
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                { 1, 0, 3, 0, 1, 0, 3, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 3, 0, 0, 2, 1 },
                { 1, 0, 3, 0, 1, 0, 0, 0, 0, 1 },
                { 1, 1, 1, 0, 1, 1, 0, 1, 1, 1 },
                { 1, 2, 0, 0, 0, 0, 0, 0, 2, 1 },
                { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1 },
                { 1, 0, 4, 0, 0, 0, 0, 2, 0, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
        };
        
        controleur.chargerNiveau(niveau);
        
        afficherInstructions();  // Affiche les instructions du jeu
        
        boolean playing = true;
        while (playing) {
            System.out.println("\nPlateau actuel :");
            afficherNiveau(controleur, niveau.length, niveau[0].length);
        
            System.out.print("\nEntrez un mouvement (z/q/s/d, x pour quitter, r pour recommencer) : ");
            String input = scanner.nextLine().toLowerCase();
        
            if (input.equals("x")) {  // Quitter le jeu
                playing = false;
                continue;
            }
        
            if (input.equals("r")) {  // Recommencer le niveau
                controleur.reinitialiserNiveau();
                continue;
            }
        
            Direction direction = convertirEntree(input);  // Convertir l'entrée utilisateur en direction
            if (direction != null) {
                controleur.action(direction);  // Déplacer le gardien
        
                if (controleur.estTermine()) {  // Vérifier si le niveau est terminé
                    System.out.println("\nFélicitations ! Vous avez terminé le niveau !");
                    afficherNiveau(controleur, niveau.length, niveau[0].length);
                    playing = false;
                }
            } else {
                System.out.println("Entrée invalide !");
            }
        }
        
        scanner.close();
        System.out.println("Merci d'avoir joué !");
    }

    // Affiche les instructions et la légende des symboles
    @objid ("c7506ab6-1481-4c68-9428-61f0e73d0d04")
    private static void afficherInstructions() {
        System.out.println("Bienvenue dans Sokoban !");
        System.out.println("Contrôles : ");
        System.out.println("  Z = HAUT");
        System.out.println("  S = BAS");
        System.out.println("  Q = GAUCHE");
        System.out.println("  D = DROITE");
        System.out.println("  X = QUITTER");
        System.out.println("  R = RECOMMENCER");
        System.out.println("\nLégende :");
        System.out.println("@ = Joueur");
        System.out.println("$ = Caisse");
        System.out.println(". = Cible");
        System.out.println("# = Mur");
        System.out.println("* = Caisse sur cible");
    }

    // Convertit l'entrée utilisateur en direction correspondante
    @objid ("dcd6d086-0dd5-4a34-9285-e45c36c9969c")
    private static Direction convertirEntree(String input) {
        switch (input) {
            case "z":
                return Direction.HAUT;
            case "s":
                return Direction.BAS;
            case "q":
                return Direction.GAUCHE;
            case "d":
                return Direction.DROITE;
            default:
                return null;  // Entrée invalide
        }
    }
}
