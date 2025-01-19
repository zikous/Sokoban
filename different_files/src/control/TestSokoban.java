package control;

import java.util.Scanner;
import entity.Direction;

public class TestSokoban {
// Méthode principale pour lancer le jeu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controleur controleur = new Controleur();
        
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
        
        afficherInstructions();
        
        boolean playing = true;
        while (playing) {
            System.out.println("\nPlateau actuel :");
            // Using the new toString method instead of afficherNiveau
            System.out.println(controleur.getEntrepot().toString());
        
            System.out.print("\nEntrez un mouvement (z/q/s/d, x pour quitter, r pour recommencer) : ");
            String input = scanner.nextLine().toLowerCase();
        
            if (input.equals("x")) {
                playing = false;
                continue;
            }
        
            if (input.equals("r")) {
                controleur.reinitialiserNiveau();
                continue;
            }
        
            Direction direction = convertirEntree(input);
            if (direction != null) {
                controleur.action(direction);
        
                if (controleur.estTermine()) {
                    System.out.println("\nFélicitations ! Vous avez terminé le niveau !");
                    System.out.println(controleur.getEntrepot().toString());
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
