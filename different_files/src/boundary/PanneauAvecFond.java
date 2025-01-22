package boundary;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PanneauAvecFond extends JPanel {
    private static final long serialVersionUID = 1L;

    private BufferedImage imageDeFond; // Image de fond pour le panneau

    public PanneauAvecFond() {
        try {
            imageDeFond = ImageIO.read(new File("images/herbe.png")); // Charge l'image de fond
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'image herbe.png : " + e.getMessage());
            imageDeFond = null; // En cas d'erreur, pas d'image de fond
        }
    }

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
