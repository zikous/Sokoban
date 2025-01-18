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
    private static final int TAILLE_CASE = 40;

// Déclaration des images
    @objid ("d5748cd7-4b72-4efd-87ac-f66365b10328")
    private BufferedImage imgMur;

    @objid ("40a6ed25-a179-403e-bcec-d656e3795f2a")
    private BufferedImage imgSol;

    @objid ("ba740fbe-18fe-4906-9fa6-8b78fc32df57")
    private BufferedImage imgCible;

    @objid ("caa900ed-39e3-43ef-b2fc-bee5f304cf53")
    private BufferedImage imgCaisse;

    @objid ("3491388b-0cf3-42fc-9227-a4534f529808")
    private BufferedImage imgCaisseSurCible;

    @objid ("2785e069-4820-4ef5-9507-4a9ed30d4474")
    private BufferedImage imgGardien;

    @objid ("821005a5-eb9a-4f47-a6fa-4c4fa18866be")
    private BufferedImage imgGardienSurCible;

    @objid ("d3750484-a764-49cd-9761-503992d7bd57")
    private Controleur controleur;

    @objid ("9cd1286c-7544-4211-9571-c1649b59ea41")
    public MonAfficheur(Controleur controleur) {
        this.controleur = controleur;
        setPreferredSize(new Dimension(800, 600));
        chargerImages();
    }

    @objid ("82d6f3f2-c611-4fb2-8a5c-59cabe8b5bb3")
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Entrepot entrepot = controleur.getEntrepot();
        
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Position pos = entrepot.getPosition(i, j);
                if (pos != null) {
                    Zone zone = pos.getZone();
                    int x = j * TAILLE_CASE;
                    int y = i * TAILLE_CASE;
        
                    // Dessiner le sol de base
                    if (imgSol != null) {
                        g2d.drawImage(imgSol, x, y, null);
                    }
        
                    // Dessiner les éléments selon leur type
                    if (zone.isEstMur()) {
                        dessinerMur(g2d, x, y);
                    } else if (zone.isEstCible() && zone.getMobile() == null) {
                        dessinerCible(g2d, x, y);
                    }
        
                    // Dessiner les mobiles
                    if (zone.getMobile() instanceof Caisse) {
                        dessinerCaisse(g2d, x, y, zone.isEstCible());
                    } else if (zone.getMobile() instanceof Gardien) {
                        dessinerGardien(g2d, x, y, zone.isEstCible());
                    }
                }
            }
        }
    }

    @objid ("a8838635-eaae-4f5f-ac9c-7278ec19b826")
    private void dessinerMur(Graphics2D g, int x, int y) {
        if (imgMur != null) {
            g.drawImage(imgMur, x, y, null);
        } else {
            // Fallback au rendu par défaut si l'image n'est pas disponible
            g.setColor(Color.DARK_GRAY);
            g.fillRect(x, y, TAILLE_CASE, TAILLE_CASE);
        }
    }

    @objid ("76580f48-4b01-4fba-9429-8fcb98b6af51")
    private void dessinerCible(Graphics2D g, int x, int y) {
        if (imgCible != null) {
            g.drawImage(imgCible, x, y, null);
        } else {
            // Fallback au rendu par défaut
            g.setColor(Color.RED);
            int marge = TAILLE_CASE / 4;
            g.fillOval(x + marge, y + marge, TAILLE_CASE - 2 * marge, TAILLE_CASE - 2 * marge);
        }
    }

    @objid ("4d159fe8-d959-4a1c-a822-ab44c536c032")
    private void dessinerCaisse(Graphics2D g, int x, int y, boolean surCible) {
        if (surCible && imgCaisseSurCible != null) {
            g.drawImage(imgCaisseSurCible, x, y, null);
        } else if (imgCaisse != null) {
            g.drawImage(imgCaisse, x, y, null);
        } else {
            // Fallback au rendu par défaut
            g.setColor(surCible ? new Color(139, 69, 19) : Color.ORANGE);
            g.fillRect(x + 2, y + 2, TAILLE_CASE - 4, TAILLE_CASE - 4);
        }
    }

    @objid ("90fb6c94-200c-47f9-a903-2a458665d1fb")
    private void dessinerGardien(Graphics2D g, int x, int y, boolean surCible) {
        if (surCible && imgGardienSurCible != null) {
            g.drawImage(imgGardienSurCible, x, y, null);
        } else if (imgGardien != null) {
            g.drawImage(imgGardien, x, y, null);
        } else {
            // Fallback au rendu par défaut
            if (surCible) {
                g.setColor(new Color(255, 192, 203));
                g.fillOval(x + 2, y + 2, TAILLE_CASE - 4, TAILLE_CASE - 4);
            }
            g.setColor(Color.BLUE);
            g.fillOval(x + 4, y + 4, TAILLE_CASE - 8, TAILLE_CASE - 8);
        }
    }

    @objid ("ad14a453-8817-4442-b2fa-728e2f122a6a")
    private void chargerImages() {
        try {
            // Charger toutes les images depuis le dossier "images"
            imgMur = ImageIO.read(new File("images/mur.png"));
            imgSol = ImageIO.read(new File("images/sol.png"));
            imgCible = ImageIO.read(new File("images/cible.png"));
            imgCaisse = ImageIO.read(new File("images/caisse.png"));
            imgCaisseSurCible = ImageIO.read(new File("images/caisse_sur_cible.png"));
            imgGardien = ImageIO.read(new File("images/gardien.png"));
            imgGardienSurCible = ImageIO.read(new File("images/gardien_sur_cible.png"));
            
            // Redimensionner les images à la taille d'une case
            imgMur = redimensionnerImage(imgMur);
            imgSol = redimensionnerImage(imgSol);
            imgCible = redimensionnerImage(imgCible);
            imgCaisse = redimensionnerImage(imgCaisse);
            imgCaisseSurCible = redimensionnerImage(imgCaisseSurCible);
            imgGardien = redimensionnerImage(imgGardien);
            imgGardienSurCible = redimensionnerImage(imgGardienSurCible);
            
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des images : " + e.getMessage());
            // En cas d'erreur, on revient au rendu par défaut avec des formes colorées
            imgMur = null;
        }
    }

    @objid ("23112b1b-092c-4ef3-8f5c-7f9bd4420cb6")
    private BufferedImage redimensionnerImage(BufferedImage originalImage) {
        BufferedImage resizedImage = new BufferedImage(TAILLE_CASE, TAILLE_CASE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(originalImage, 0, 0, TAILLE_CASE, TAILLE_CASE, null);
        g.dispose();
        return resizedImage;
    }

}
