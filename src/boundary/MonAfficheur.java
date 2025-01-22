package boundary;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controleur;
import entity.*;

@objid ("52ef6f21-2604-4f8f-8bea-0a23c5eacf4d")
public class MonAfficheur extends JPanel {
    @objid ("293286de-b362-41ac-8cd0-db3df6ac97a8")
    private static final int TAILLE_CASE = 40; // Taille d'une case en pixels

    @objid ("d5748cd7-4b72-4efd-87ac-f66365b10328")
    private BufferedImage imgMur; // Image pour les murs

    @objid ("40a6ed25-a179-403e-bcec-d656e3795f2a")
    private BufferedImage imgSol; // Image pour le sol

    @objid ("ba740fbe-18fe-4906-9fa6-8b78fc32df57")
    private BufferedImage imgCible; // Image pour les cibles

    @objid ("caa900ed-39e3-43ef-b2fc-bee5f304cf53")
    private BufferedImage imgCaisse; // Image pour les caisses

    @objid ("3491388b-0cf3-42fc-9227-a4534f529808")
    private BufferedImage imgCaisseSurCible; // Image pour les caisses sur cibles

    @objid ("9bcf29e4-4ca9-415e-bf56-c3a896935fbd")
    private static final long serialVersionUID = 1L;

    @objid ("16ac0ead-f9f7-4dfb-8b00-bbe2d717d666")
    private BufferedImage imgGardienHaut; // Image du gardien vers le haut

    @objid ("4391e953-fa6e-416a-84b0-a4727b08d446")
    private BufferedImage imgGardienBas; // Image du gardien vers le bas

    @objid ("bf27c7f3-0c97-434a-bff9-b3e9a53a9795")
    private BufferedImage imgGardienGauche; // Image du gardien vers la gauche

    @objid ("04873657-e3a5-47d0-a54c-1b2ee82bc70d")
    private BufferedImage imgGardienDroite; // Image du gardien vers la droite

    @objid ("809a9762-1041-4dbd-ab92-b6af10559807")
    private BufferedImage imgHerbe; // Image de fond (herbe)

    @objid ("d3750484-a764-49cd-9761-503992d7bd57")
    private Controleur controleur; // Contrôleur du jeu

    @objid ("9cd1286c-7544-4211-9571-c1649b59ea41")
    public MonAfficheur(Controleur controleur) {
        this.controleur = controleur;
        setPreferredSize(new Dimension(800, 600)); // Taille du panneau
        chargerImages(); // Charge les images
    }

    @objid ("82d6f3f2-c611-4fb2-8a5c-59cabe8b5bb3")
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

    @objid ("a8838635-eaae-4f5f-ac9c-7278ec19b826")
    private void dessinerMur(Graphics g, int x, int y) {
        if (imgMur != null) {
            g.drawImage(imgMur, x, y, null); // Dessine l'image du mur
        } else {
            g.setColor(Color.DARK_GRAY); // Couleur par défaut si l'image est manquante
            g.fillRect(x, y, TAILLE_CASE, TAILLE_CASE);
        }
    }

    @objid ("76580f48-4b01-4fba-9429-8fcb98b6af51")
    private void dessinerCible(Graphics g, int x, int y) {
        if (imgCible != null) {
            g.drawImage(imgCible, x, y, null); // Dessine l'image de la cible
        } else {
            g.setColor(Color.RED); // Couleur par défaut si l'image est manquante
            int marge = TAILLE_CASE / 4;
            g.fillOval(x + marge, y + marge, TAILLE_CASE - 2 * marge, TAILLE_CASE - 2 * marge);
        }
    }

    @objid ("4d159fe8-d959-4a1c-a822-ab44c536c032")
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

    @objid ("90fb6c94-200c-47f9-a903-2a458665d1fb")
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

    @objid ("ad14a453-8817-4442-b2fa-728e2f122a6a")
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

    @objid ("d52c1df9-0179-4ea0-aa26-21a1386c22ce")
    private void dessinerHerbe(Graphics g, int x, int y) {
        if (imgHerbe != null) {
            g.drawImage(imgHerbe, x, y, null); // Dessine l'herbe
        } else {
            g.setColor(Color.GREEN); // Couleur par défaut si l'image est manquante
            g.fillRect(x, y, TAILLE_CASE, TAILLE_CASE);
        }
    }

    @objid ("6950601e-9b59-4138-9a85-71f00a3152e9")
    private void dessinerSol(Graphics g, int x, int y) {
        if (imgSol != null) {
            g.drawImage(imgSol, x, y, null); // Dessine le sol
        }
    }

    @objid ("0671d5b5-d53c-4f67-9444-2c9be6acbb44")
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

    @objid ("6ff5f19d-704d-4f79-9853-b73ea670f89c")
    private BufferedImage chargerEtRedimensionnerImage(String chemin) throws IOException {
        BufferedImage imageOriginale = ImageIO.read(new File(chemin)); // Charge l'image
        BufferedImage imageRedimensionnee = new BufferedImage(TAILLE_CASE, TAILLE_CASE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imageRedimensionnee.createGraphics();
        g.drawImage(imageOriginale, 0, 0, TAILLE_CASE, TAILLE_CASE, null); // Redimensionne l'image
        g.dispose();
        return imageRedimensionnee;
    }

}
