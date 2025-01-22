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

@objid ("300cc6b9-f489-4167-a57d-84ebfd6a60f7")
public class PanneauAvecFond extends JPanel {
    @objid ("798f898d-3e05-43ec-801f-6c5876546537")
    private static final long serialVersionUID = 1L;

    @objid ("42eacbf9-19da-4d8a-b96c-70850872738c")
    private BufferedImage imageDeFond; // Image de fond pour le panneau

    @objid ("987d4085-9376-4c9b-b969-06a47113ad0c")
    public PanneauAvecFond() {
        try {
            imageDeFond = ImageIO.read(new File("images/herbe.png")); // Charge l'image de fond
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'image herbe.png : " + e.getMessage());
            imageDeFond = null; // En cas d'erreur, pas d'image de fond
        }
    }

    @objid ("7db52dd6-944b-405d-8238-2f76f5d349a8")
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imageDeFond != null) {
            int largeur = getWidth();
            int hauteur = getHeight();
            int largeurImage = imageDeFond.getWidth();
            int hauteurImage = imageDeFond.getHeight();
        
            // Dessine l'image de fond en mosaïque
            for (int x = 0; x < largeur; x += largeurImage) {
                for (int y = 0; y < hauteur; y += hauteurImage) {
                    g.drawImage(imageDeFond, x, y, null);
                }
            }
        } else {
            // Si l'image de fond n'est pas chargée, utilise une couleur de fond verte
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

}
