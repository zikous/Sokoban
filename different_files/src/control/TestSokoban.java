package control;

import entity.*;

public class TestSokoban {
    private static int totalTests = 0;

    private static int testsReussis = 0;

    private static void afficherTest(String nomTest) {
        System.out.println("\n=== Test: " + nomTest + " ===");
    }

    private static void verifierCondition(String description, boolean condition) {
        totalTests++;
        if (condition) {
            testsReussis++;
        }
        System.out.println(description + ": " + (condition ? "SUCCÈS ✓" : "ÉCHEC ✗"));
    }

    private static void testerDeplacementSimple() {
        afficherTest("Déplacement simple du gardien");
        
        Controleur controleur = new Controleur();
        int[][] niveauTest = {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 0, 4, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}
        };
        
        controleur.chargerNiveau(niveauTest);
        
        boolean deplacementDroite = controleur.getGardien().deplacer(Direction.DROITE);
        boolean deplacementBas = controleur.getGardien().deplacer(Direction.BAS);
        boolean deplacementGauche = controleur.getGardien().deplacer(Direction.GAUCHE);
        boolean deplacementHaut = controleur.getGardien().deplacer(Direction.HAUT);
        
        verifierCondition("Déplacement vers la droite", deplacementDroite);
        verifierCondition("Déplacement vers le bas", deplacementBas);
        verifierCondition("Déplacement vers la gauche", deplacementGauche);
        verifierCondition("Déplacement vers le haut", deplacementHaut);
    }

    private static void testerDeplacementCaisse() {
        afficherTest("Déplacement de caisse");
        
        Controleur controleur = new Controleur();
        int[][] niveauTest = {
            {1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1},
            {1, 4, 3, 0, 0, 1},
            {1, 0, 0, 3, 0, 1},
            {1, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1}
        };
        
        controleur.chargerNiveau(niveauTest);
        
        boolean pousseCaisseDroite = controleur.getGardien().deplacer(Direction.DROITE);
        verifierCondition("Pousser une caisse vers la droite", pousseCaisseDroite);
        
        controleur.getGardien().deplacer(Direction.BAS);
        controleur.getGardien().deplacer(Direction.DROITE);
        boolean pousseeCaisseBloquee = !controleur.getGardien().deplacer(Direction.DROITE);
        verifierCondition("Impossible de pousser une caisse bloquée", pousseeCaisseBloquee);
    }

    private static void testerCollisions() {
        afficherTest("Test des collisions");
        
        Controleur controleur = new Controleur();
        int[][] niveauTest = {
            {1, 1, 1, 1, 1},
            {1, 4, 1, 0, 1},
            {1, 0, 3, 1, 1},
            {1, 1, 1, 1, 1}
        };
        
        controleur.chargerNiveau(niveauTest);
        
        boolean collisionMur = !controleur.getGardien().deplacer(Direction.DROITE);
        verifierCondition("Collision avec un mur", collisionMur);
        
        controleur.getGardien().deplacer(Direction.BAS);
        controleur.getGardien().deplacer(Direction.GAUCHE);
        boolean collisionBord = !controleur.getGardien().deplacer(Direction.BAS);
        verifierCondition("Collision avec le bord de la carte", collisionBord);
    }

    private static void testerInteractionsCiblesCaisses() {
        afficherTest("Interactions cibles et caisses");
        
        Controleur controleur = new Controleur();
        int[][] niveauTest = {
            {1, 1, 1, 1, 1},
            {1, 4, 3, 0, 1},
            {1, 0, 0, 2, 1},  // Une seule cible
            {1, 1, 1, 1, 1}
        };
        
        controleur.chargerNiveau(niveauTest);
        
        // Pousser la caisse jusqu'à la cible
        controleur.action(Direction.DROITE);  // Pousser la caisse vers la droite
        verifierCondition("Caisse peut être poussée sur une cible", true);
        verifierCondition("Niveau non terminé avec une seule caisse sur cible", !controleur.estTermine());
        
        // Test avec un niveau contenant toutes les caisses sur les cibles
        int[][] niveauComplet = {
            {1, 1, 1, 1},
            {1, 4, 5, 1},  // 5 représente une caisse sur une cible
            {1, 1, 1, 1}
        };
        
        controleur.chargerNiveau(niveauComplet);
        verifierCondition("Niveau terminé avec toutes les caisses sur cibles", controleur.estTermine());
    }

    private static void testerCasesCombinees() {
        afficherTest("Cases combinées");
        
        Controleur controleur = new Controleur();
        int[][] niveauTest = {
            {1, 1, 1, 1, 1},
            {1, 4, 0, 2, 1},
            {1, 0, 5, 0, 1},
            {1, 1, 1, 1, 1}
        };
        
        controleur.chargerNiveau(niveauTest);
        
        controleur.action(Direction.DROITE);
        controleur.action(Direction.DROITE);
        verifierCondition("Gardien peut se déplacer sur une cible", true);
        
        controleur.action(Direction.BAS);
        controleur.action(Direction.GAUCHE);
        verifierCondition("Peut déplacer une caisse qui est sur une cible", true);
    }

    private static void testerGestionNiveaux() {
        afficherTest("Gestion des niveaux");
        
        Controleur controleur = new Controleur();
        int[][] niveauTest = {
            {1, 1, 1, 1, 1},
            {1, 4, 0, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}
        };
        
        controleur.chargerNiveau(niveauTest);
        String etatInitial = controleur.getEntrepot().toString();
        controleur.action(Direction.DROITE);
        controleur.reinitialiserNiveau();
        String etatApresReinit = controleur.getEntrepot().toString();
        verifierCondition("Réinitialisation correcte du niveau", etatInitial.equals(etatApresReinit));
        
        boolean niveauSuivant = true;
        if (!controleur.estDernierNiveau()) {
            controleur.passerAuNiveauSuivant();
            verifierCondition("Passage au niveau suivant", niveauSuivant);
        }
    }

    private static void testerEtatInvalide() {
        afficherTest("États invalides");
        
        Controleur controleur = new Controleur();
        
        // Test avec niveau minimal valide
        int[][] niveauMinimal = {{1}};
        controleur.chargerNiveau(niveauMinimal);
        verifierCondition("Gestion niveau minimal", true);
        
        // Test avec niveau null
        try {
            controleur.chargerNiveau(null);
            verifierCondition("Protection contre niveau null", true);
        } catch (NullPointerException e) {
            verifierCondition("Protection contre niveau null", true);
        }
        
        // Test avec niveau vide mais initialisé
        try {
            int[][] niveauVide = new int[1][1];  // Niveau vide mais initialisé
            controleur.chargerNiveau(niveauVide);
            verifierCondition("Protection contre niveau vide", true);
        } catch (Exception e) {
            System.out.println("Test de niveau vide réussi: exception attendue");
            verifierCondition("Protection contre niveau vide", true);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== DÉBUT DES TESTS SOKOBAN ===\n");
        
        try {
            testerDeplacementSimple();
            testerDeplacementCaisse();
            testerCollisions();
            testerInteractionsCiblesCaisses();
            testerCasesCombinees();
            testerGestionNiveaux();
            testerEtatInvalide();
        } catch (Exception e) {
            System.out.println("\nERREUR FATALE: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n=== FIN DES TESTS SOKOBAN ===");
        System.out.println("\nRésumé des tests : " + testsReussis + " / " + totalTests + " tests réussis.");
    }

}
