package boundary;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import control.Controleur;
import entity.*;

public class MonAfficheur extends JPanel {
    private static final int TAILLE_CASE = 40;

    private BufferedImage imgMur;

    private BufferedImage imgSol;

    private BufferedImage imgCible;

    private BufferedImage imgCaisse;

    private BufferedImage imgCaisseSurCible;

    private static final long serialVersionUID = 1L;

    private BufferedImage imgGardienHaut;

    private BufferedImage imgGardienBas;

    private BufferedImage imgGardienGauche;

    private BufferedImage imgGardienDroite;

    private Controleur controleur;

    public MonAfficheur(Controleur controleur) {
        this.controleur = controleur;
        setPreferredSize(new Dimension(800, 600));
        chargerImages();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Entrepot entrepot = controleur.getEntrepot();
        
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Position pos = entrepot.getPosition(i, j);
                if (pos != null) {
                    Zone zone = pos.getZone();
                    int x = j * TAILLE_CASE;
                    int y = i * TAILLE_CASE;
        
                    if (imgSol != null) {
                        g.drawImage(imgSol, x, y, null);
                    }
        
                    if (zone.isEstMur()) {
                        dessinerMur(g, x, y);
                    } else if (zone.isEstCible() && zone.getMobile() == null) {
                        dessinerCible(g, x, y);
                    }
        
                    if (zone.getMobile() instanceof Caisse) {
                        dessinerCaisse(g, x, y, zone.isEstCible());
                    } else if (zone.getMobile() instanceof Gardien) {
                        dessinerGardien(g, x, y, zone.isEstCible());
                    }
                }
            }
        }
    }

    private void dessinerMur(Graphics g, int x, int y) {
        if (imgMur != null) {
            g.drawImage(imgMur, x, y, null);
        } else {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(x, y, TAILLE_CASE, TAILLE_CASE);
        }
    }

    private void dessinerCible(Graphics g, int x, int y) {
        if (imgCible != null) {
            g.drawImage(imgCible, x, y, null);
        } else {
            g.setColor(Color.RED);
            int marge = TAILLE_CASE / 4;
            g.fillOval(x + marge, y + marge, TAILLE_CASE - 2 * marge, TAILLE_CASE - 2 * marge);
        }
    }

    private void dessinerCaisse(Graphics g, int x, int y, boolean surCible) {
        if (surCible && imgCaisseSurCible != null) {
            g.drawImage(imgCaisseSurCible, x, y, null);
        } else if (imgCaisse != null) {
            g.drawImage(imgCaisse, x, y, null);
        } else {
            g.setColor(surCible ? new Color(139, 69, 19) : Color.ORANGE);
            g.fillRect(x + 2, y + 2, TAILLE_CASE - 4, TAILLE_CASE - 4);
        }
    }

    private void dessinerGardien(Graphics g, int x, int y, boolean surCible) {
        BufferedImage imageGardien = null;
        
        Direction direction = controleur.getGardien().getCurrentDirection();
        
        switch (direction) {
            case HAUT:
                imageGardien = imgGardienHaut;
                break;
            case BAS:
                imageGardien = imgGardienBas;
                break;
            case GAUCHE:
                imageGardien = imgGardienGauche;
                break;
            case DROITE:
                imageGardien = imgGardienDroite;
                break;
        }
        
        if (surCible) {
            if (imgCible != null) {
                g.drawImage(imgCible, x, y, null);
            } else {
                g.setColor(Color.RED);
                int marge = TAILLE_CASE / 4;
                g.fillOval(x + marge, y + marge, TAILLE_CASE - 2 * marge, TAILLE_CASE - 2 * marge);
            }
        }
        
        if (imageGardien != null) {
            g.drawImage(imageGardien, x, y, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillOval(x + 4, y + 4, TAILLE_CASE - 8, TAILLE_CASE - 8);
        }
    }

    private void chargerImages() {
        try {
            imgMur = chargerEtRedimensionnerImage("images/mur.png");
            imgSol = chargerEtRedimensionnerImage("images/sol.png");
            imgCible = chargerEtRedimensionnerImage("images/cible.png");
            imgCaisse = chargerEtRedimensionnerImage("images/caisse.png");
            imgCaisseSurCible = chargerEtRedimensionnerImage("images/caisse_sur_cible.png");
        
            imgGardienHaut = chargerEtRedimensionnerImage("images/gardien_haut.png");
            imgGardienBas = chargerEtRedimensionnerImage("images/gardien_bas.png");
            imgGardienGauche = chargerEtRedimensionnerImage("images/gardien_gauche.png");
            imgGardienDroite = chargerEtRedimensionnerImage("images/gardien_droite.png");
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des images : " + e.getMessage());
            imgMur = null;
            imgSol = null;
            imgCible = null;
            imgCaisse = null;
            imgCaisseSurCible = null;
            imgGardienHaut = null;
            imgGardienBas = null;
            imgGardienGauche = null;
            imgGardienDroite = null;
        }
    }

    private BufferedImage chargerEtRedimensionnerImage(String chemin) throws IOException {
        BufferedImage imageOriginale = ImageIO.read(new File(chemin));
        BufferedImage imageRedimensionnee = new BufferedImage(TAILLE_CASE, TAILLE_CASE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imageRedimensionnee.createGraphics();
        g.drawImage(imageOriginale, 0, 0, TAILLE_CASE, TAILLE_CASE, null);
        g.dispose();
        return imageRedimensionnee;
    }

}
