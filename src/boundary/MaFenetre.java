package boundary;

import java.awt.event.*;
import javax.swing.*;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controleur;
import entity.*;

@objid ("2c98f262-fb7c-420e-8e27-d7b44a78c0dc")
public class MaFenetre extends JFrame implements KeyListener {
    @objid ("13aca0bc-7152-4285-9171-a1d2c2c1a376")
    private static final long serialVersionUID = 1L;

    @objid ("11ffa188-82b6-4d06-8a90-741d509ba440")
    private MonAfficheur monAfficheur;

    @objid ("874f95fe-2ef0-450d-96b6-32b60de5319b")
    private Controleur controleur;

    @objid ("4dc4b2fe-2142-4d76-9dd9-bda64da17297")
    public MaFenetre(Controleur controleur) {
        super("Sokoban");
        this.controleur = controleur; 
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Ferme l'application lors de la fermeture de la fenêtre
        this.monAfficheur = new MonAfficheur(controleur); 
        add(monAfficheur);  // Ajoute l'afficheur au JFrame
        
        addKeyListener(this);  // Ajoute l'écouteur de touches
        setFocusable(true);  // Permet à la fenêtre de capter les événements clavier
        pack();  // Ajuste la taille de la fenêtre
        setLocationRelativeTo(null);  // Centre la fenêtre
        setVisible(true);  // Rends la fenêtre visible
    }

    @objid ("682aa214-9b02-4dff-bdb9-8ba4997aba72")
    @Override
    public void keyPressed(KeyEvent e) {
        Direction direction = null;
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                direction = Direction.HAUT;
                break;
            case KeyEvent.VK_DOWN:
                direction = Direction.BAS;
                break;
            case KeyEvent.VK_LEFT:
                direction = Direction.GAUCHE;
                break;
            case KeyEvent.VK_RIGHT:
                direction = Direction.DROITE;
                break;
            case KeyEvent.VK_R:
                controleur.reinitialiserNiveau();  // Réinitialise le niveau si "R" est pressé
                break;
        }
        
        if (direction != null) {
            controleur.action(direction);  // Applique l'action dans le contrôleur
        }
        
        monAfficheur.repaint();  // Rafraîchit l'affichage
        if (controleur.estTermine()) {  // Vérifie si le niveau est terminé
            JOptionPane.showMessageDialog(this, "Niveau terminé !", "Félicitations", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @objid ("6d546574-dca0-4213-8bd4-35407aead6a9")
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @objid ("d796e4a7-3eed-4144-9060-a4eb1e368c01")
    @Override
    public void keyReleased(KeyEvent e) {
    }

}
