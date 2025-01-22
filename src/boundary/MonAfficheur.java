package boundary;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JPanel;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controleur;
import entity.*;

@objid ("52ef6f21-2604-4f8f-8bea-0a23c5eacf4d")
public class MonAfficheur extends JPanel {
    @objid ("38bc53e9-4b95-4faf-bd63-eb4fc812c634")
    private static final int TAILLE_CASE = 40; // Taille d'une case en pixels

    @objid ("5e368795-46ac-40db-9aad-6a34e2347231")
    private static final long serialVersionUID = 1L;

    @objid ("d3750484-a764-49cd-9761-503992d7bd57")
    private Controleur controleur; // Contrôleur du jeu

    @objid ("a2c1e745-60d1-41b0-a7f2-4465150a02d1")
    private BufferedImage imgMur; // Image pour les murs

    @objid ("1f7d09b5-397d-4b02-825c-d48965bff7a0")
    private BufferedImage imgSol; // Image pour le sol

    @objid ("95094994-707b-4b01-83c5-e69f315b502b")
    private BufferedImage imgCible; // Image pour les cibles

    @objid ("3adeb3d2-511a-41ce-8362-16c642cb0485")
    private BufferedImage imgCaisse; // Image pour les caisses

    @objid ("39b91b0d-3279-4a6b-840d-47224f092751")
    private BufferedImage imgCaisseSurCible; // Image pour les caisses sur cibles

    @objid ("aa242227-a831-4017-8851-7f421b46b1ae")
    private BufferedImage imgGardienHaut; // Image du gardien vers le haut

    @objid ("da31aae2-5daf-403c-8132-c97b694258bd")
    private BufferedImage imgGardienBas; // Image du gardien vers le bas

    @objid ("d341557a-7e8f-40d4-af64-649f13910176")
    private BufferedImage imgGardienGauche; // Image du gardien vers la gauche

    @objid ("fdcc7455-24b9-453f-b903-58ab15bed005")
    private BufferedImage imgGardienDroite; // Image du gardien vers la droite

    @objid ("e1c46379-b7df-4c0b-a08d-da11c0f1ef84")
    private BufferedImage imgHerbe; // Image de fond (herbe)

    @objid ("89f440a6-bece-453a-b1f2-5ffb2df8a6db")
    public MonAfficheur(Controleur controleur) {
        this.controleur = controleur;
        setPreferredSize(new Dimension(800, 600)); // Taille du panneau
        chargerImages(); // Charge les images
    }

    @objid ("3b5fe7e9-de23-451f-808d-dcb0b101f51d")
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

    @objid ("cfa09f0c-c77a-4a5c-9a13-c1809eaee0dc")
    private void dessinerMur(Graphics g, int x, int y) {
        if (imgMur != null) {
            g.drawImage(imgMur, x, y, null); // Dessine l'image du mur
        } else {
            g.setColor(Color.DARK_GRAY); // Couleur par défaut si l'image est manquante
            g.fillRect(x, y, TAILLE_CASE, TAILLE_CASE);
        }
    }

    @objid ("f925c588-7012-459a-99fe-6b4625ee7e25")
    private void dessinerCible(Graphics g, int x, int y) {
        if (imgCible != null) {
            g.drawImage(imgCible, x, y, null); // Dessine l'image de la cible
        } else {
            g.setColor(Color.RED); // Couleur par défaut si l'image est manquante
            int marge = TAILLE_CASE / 4;
            g.fillOval(x + marge, y + marge, TAILLE_CASE - 2 * marge, TAILLE_CASE - 2 * marge);
        }
    }

    @objid ("7e43c476-494b-4eac-b448-cb2b7d175b87")
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

    @objid ("e6b9bb09-e857-4dea-9a97-639b3f150802")
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

    @objid ("2d6f226a-0507-48ed-ba77-3d748481f9ff")
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

    @objid ("f5f870fb-986d-4495-afba-aa2367809921")
    private void dessinerHerbe(Graphics g, int x, int y) {
        if (imgHerbe != null) {
            g.drawImage(imgHerbe, x, y, null); // Dessine l'herbe
        } else {
            g.setColor(Color.GREEN); // Couleur par défaut si l'image est manquante
            g.fillRect(x, y, TAILLE_CASE, TAILLE_CASE);
        }
    }

    @objid ("2fa76d9b-f392-4163-bd2c-6bdb8aae3074")
    private void dessinerSol(Graphics g, int x, int y) {
        if (imgSol != null) {
            g.drawImage(imgSol, x, y, null); // Dessine le sol
        }
    }

    @objid ("88b4a0ba-6f0e-4753-85b3-137224e9e1cd")
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

    @objid ("e22e7d5e-9720-4a3e-b1f7-6e60d16a3517")
    private BufferedImage chargerEtRedimensionnerImage(String chemin) throws IOException {
        BufferedImage imageOriginale = ImageIO.read(new File(chemin)); // Charge l'image
        BufferedImage imageRedimensionnee = new BufferedImage(TAILLE_CASE, TAILLE_CASE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imageRedimensionnee.createGraphics();
        g.drawImage(imageOriginale, 0, 0, TAILLE_CASE, TAILLE_CASE, null); // Redimensionne l'image
        g.dispose();
        return imageRedimensionnee;
    }

}
