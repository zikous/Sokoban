package boundary;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PanneauAvecFond extends JPanel {
    private static final long serialVersionUID = 1L;

    private BufferedImage imageDeFond;

    public PanneauAvecFond() {
        try {
            imageDeFond = ImageIO.read(new File("images/herbe.png"));
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'image herbe.png : " + e.getMessage());
            imageDeFond = null;
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
        
            for (int x = 0; x < largeur; x += largeurImage) {
                for (int y = 0; y < hauteur; y += hauteurImage) {
                    g.drawImage(imageDeFond, x, y, null);
                }
            }
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

}
