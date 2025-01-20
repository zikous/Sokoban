package boundary;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("300cc6b9-f489-4167-a57d-84ebfd6a60f7")
public class PanneauAvecFond extends JPanel {
    @objid ("74d5a686-1203-4001-ab06-8af6a92a43b9")
    private static final long serialVersionUID = 1L;

    @objid ("f3b20858-f1b9-4d77-aec2-a74382cc72c8")
    private BufferedImage imageDeFond;

    @objid ("12618bb8-9473-4fb4-b8b9-b7bf9a135249")
    public PanneauAvecFond() {
        try {
            imageDeFond = ImageIO.read(new File("images/herbe.png"));
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'image herbe.png : " + e.getMessage());
            imageDeFond = null;
        }
    }

    @objid ("2bdbd24b-15b4-4c5d-aaaa-f0d8da443f9f")
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
