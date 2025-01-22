package boundary;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JPanel;
import control.Controleur;
import entity.*;

public class MonAfficheur extends JPanel {
    private static final int TAILLE_CASE = 40; // Taille d'une case en pixels

    private static final long serialVersionUID = 1L;

    private Controleur controleur; // Contrôleur du jeu

    private BufferedImage imgMur; // Image pour les murs

    private BufferedImage imgSol; // Image pour le sol

    private BufferedImage imgCible; // Image pour les cibles

    private BufferedImage imgCaisse; // Image pour les caisses

    private BufferedImage imgCaisseSurCible; // Image pour les caisses sur cibles

    private BufferedImage imgGardienHaut; // Image du gardien vers le haut

    private BufferedImage imgGardienBas; // Image du gardien vers le bas

    private BufferedImage imgGardienGauche; // Image du gardien vers la gauche

    private BufferedImage imgGardienDroite; // Image du gardien vers la droite

    private BufferedImage imgHerbe; // Image de fond (herbe)

    public MonAfficheur(Controleur controleur) {
        this.controleur = controleur;
        setPreferredSize(new Dimension(800, 600)); // Taille du panneau
        chargerImages(); // Charge les images
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessine l'herbe en arrière-plan
        for (int x = 0; x < getWidth(); x += TAILLE_CASE) {
            for (int y = 0; y < getHeight(); y += TAILLE_CASE) {
                dessinerHerbe(g, x, y);
            }
        }
        
        // Parcourt toutes les cases de l'entrepôt pour les afficher
        Entrepot entrepot = controleur.getEntrepot();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Position pos = entrepot.getPosition(i, j);
                int x = j * TAILLE_CASE;
                int y = i * TAILLE_CASE;
                if (pos != null) {
                    Zone zone = pos.getZone();
                    dessinerSol(g, x, y); // Dessine le sol
                    if (zone.isEstMur()) {
                        dessinerMur(g, x, y); // Dessine un mur
                    } else if (zone.isEstCible() && zone.getMobile() == null) {
                        dessinerCible(g, x, y); // Dessine une cible vide
                    }
                    if (zone.getMobile() instanceof Caisse) {
                        dessinerCaisse(g, x, y, zone.isEstCible()); // Dessine une caisse
                    } else if (zone.getMobile() instanceof Gardien) {
                        dessinerGardien(g, x, y, zone.isEstCible()); // Dessine le gardien
                    }
                }
            }
        }
    }

    private void dessinerMur(Graphics g, int x, int y) {
        if (imgMur != null) {
            g.drawImage(imgMur, x, y, null); // Dessine l'image du mur
        } else {
            g.setColor(Color.DARK_GRAY); // Couleur par défaut si l'image est manquante
            g.fillRect(x, y, TAILLE_CASE, TAILLE_CASE);
        }
    }

    private void dessinerCible(Graphics g, int x, int y) {
        if (imgCible != null) {
            g.drawImage(imgCible, x, y, null); // Dessine l'image de la cible
        } else {
            g.setColor(Color.RED); // Couleur par défaut si l'image est manquante
            int marge = TAILLE_CASE / 4;
            g.fillOval(x + marge, y + marge, TAILLE_CASE - 2 * marge, TAILLE_CASE - 2 * marge);
        }
    }

    private void dessinerCaisse(Graphics g, int x, int y, boolean surCible) {
        if (surCible && imgCaisseSurCible != null) {
            g.drawImage(imgCaisseSurCible, x, y, null); // Dessine une caisse sur cible
        } else if (imgCaisse != null) {
            g.drawImage(imgCaisse, x, y, null); // Dessine une caisse normale
        } else {
            g.setColor(surCible ? new Color(139, 69, 19) : Color.ORANGE); // Couleurs par défaut
            g.fillRect(x + 2, y + 2, TAILLE_CASE - 4, TAILLE_CASE - 4);
        }
    }

    private void dessinerGardien(Graphics g, int x, int y, boolean surCible) {
        BufferedImage imageGardien = getImageGardien(); // Obtient l'image du gardien selon sa direction
        if (surCible) {
            dessinerCible(g, x, y); // Dessine la cible sous le gardien
        }
        if (imageGardien != null) {
            g.drawImage(imageGardien, x, y, null); // Dessine le gardien
        } else {
            g.setColor(Color.BLUE); // Couleur par défaut si l'image est manquante
            g.fillOval(x + 4, y + 4, TAILLE_CASE - 8, TAILLE_CASE - 8);
        }
    }

    private void chargerImages() {
        try {
            // Charge et redimensionne toutes les images
            imgMur = chargerEtRedimensionnerImage("images/mur.png");
            imgSol = chargerEtRedimensionnerImage("images/sol.png");
            imgCible = chargerEtRedimensionnerImage("images/cible.png");
            imgCaisse = chargerEtRedimensionnerImage("images/caisse.png");
            imgCaisseSurCible = chargerEtRedimensionnerImage("images/caisse_sur_cible.png");
            imgHerbe = chargerEtRedimensionnerImage("images/herbe.png");
            imgGardienHaut = chargerEtRedimensionnerImage("images/gardien_haut.png");
            imgGardienBas = chargerEtRedimensionnerImage("images/gardien_bas.png");
            imgGardienGauche = chargerEtRedimensionnerImage("images/gardien_gauche.png");
            imgGardienDroite = chargerEtRedimensionnerImage("images/gardien_droite.png");
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des images : " + e.getMessage());
        }
    }

    private void dessinerHerbe(Graphics g, int x, int y) {
        if (imgHerbe != null) {
            g.drawImage(imgHerbe, x, y, null); // Dessine l'herbe
        } else {
            g.setColor(Color.GREEN); // Couleur par défaut si l'image est manquante
            g.fillRect(x, y, TAILLE_CASE, TAILLE_CASE);
        }
    }

    private void dessinerSol(Graphics g, int x, int y) {
        if (imgSol != null) {
            g.drawImage(imgSol, x, y, null); // Dessine le sol
        }
    }

    private BufferedImage getImageGardien() {
        Direction direction = controleur.getGardien().getCurrentDirection();
        switch (direction) {
            case HAUT: return imgGardienHaut;
            case BAS: return imgGardienBas;
            case GAUCHE: return imgGardienGauche;
            case DROITE: return imgGardienDroite;
            default: return null;
        }
    }

    private BufferedImage chargerEtRedimensionnerImage(String chemin) throws IOException {
        BufferedImage imageOriginale = ImageIO.read(new File(chemin)); // Charge l'image
        BufferedImage imageRedimensionnee = new BufferedImage(TAILLE_CASE, TAILLE_CASE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imageRedimensionnee.createGraphics();
        g.drawImage(imageOriginale, 0, 0, TAILLE_CASE, TAILLE_CASE, null); // Redimensionne l'image
        g.dispose();
        return imageRedimensionnee;
    }

}
