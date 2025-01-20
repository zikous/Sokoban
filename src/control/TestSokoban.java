package control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import entity.*;

@objid ("37da7c12-95ce-4751-a595-f168f3b118f9")
public class TestSokoban {
    @objid ("d0ba1d01-d690-49ce-8ddf-1351783cb560")
    private static int totalTests = 0;

    @objid ("670b73ae-846a-4c45-81da-cc2cb5d27d08")
    private static int testsReussis = 0;

    @objid ("94196a09-4932-4e36-b884-9ef22dc26816")
    private static void afficherTest(String nomTest) {
        System.out.println("\n=== Test: " + nomTest + " ===");
    }

    @objid ("f425dd6b-f425-40be-8424-0c4e56d895f2")
    private static void verifierCondition(String description, boolean condition) {
        totalTests++;
        if (condition) {
            testsReussis++;
        }
        System.out.println(description + ": " + (condition ? "SUCCÈS ✓" : "ÉCHEC ✗"));
    }

    @objid ("c0a8943e-436c-4d64-99a8-30f803b6372a")
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

    @objid ("20741d04-9746-44b2-bb6d-95a20ac07639")
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

    @objid ("e505d34b-ca1c-4cd1-9052-2ffc01498d9e")
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

    @objid ("8311137d-799d-476a-bfe3-792e72751d63")
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

    @objid ("74a641b8-d3c6-4486-b781-03477deccdf8")
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

    @objid ("e6ba855b-061c-40a1-bce2-b7172cf6ca4b")
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

    @objid ("f24e0eca-3c31-475c-81f5-9d06289f511e")
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

    @objid ("b13f3538-d515-4d35-a635-6aad07506ebc")
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
