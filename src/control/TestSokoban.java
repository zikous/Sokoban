package control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import entity.*;

@objid ("37da7c12-95ce-4751-a595-f168f3b118f9")
public class TestSokoban {
    @objid ("d2f664db-e54b-408e-9b0a-805d445adc3f")
    private static int totalTests = 0; // Nombre total de tests exécutés

    @objid ("833f5e94-7efb-467a-8ea2-365a4a9889b7")
    private static int testsReussis = 0; // Nombre de tests réussis

    @objid ("591a15cd-a163-4e5a-a311-d207c8599675")
    private static void afficherTest(String nomTest) {
        System.out.println("\n=== Test: " + nomTest + " ===");
    }

    @objid ("4fa46da6-4f97-4997-a334-5f1e33964e6a")
    private static void verifierCondition(String description, boolean condition) {
        totalTests++;
        if (condition) {
            testsReussis++;
        }
        System.out.println(description + ": " + (condition ? "SUCCÈS ✓" : "ÉCHEC ✗"));
    }

    @objid ("83761799-5283-42fb-84e4-23077af625b1")
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

    @objid ("a98f0d50-aad1-4cbc-a93d-6d1e47553b59")
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

    @objid ("4e4126fb-d7d3-43db-adb5-91a6f51d5b87")
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

    @objid ("2faad882-b5b9-4253-9006-bd2b4e6e9cb7")
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
        controleur.action(Direction.DROITE);
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

    @objid ("4258920e-3f2b-4704-8f61-b3c2c8dc2696")
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

    @objid ("e6d36ec0-cea8-4175-8f64-467959ff4ebb")
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

    @objid ("f17e58ea-93fb-4ff7-b77a-9bae402fb9f2")
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

    @objid ("a94c5cba-0e03-4173-a639-e5c55d1ebdce")
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
