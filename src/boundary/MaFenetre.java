package boundary;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.JFrame;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controleur;
import entity.Direction;

@objid ("2c98f262-fb7c-420e-8e27-d7b44a78c0dc")
public class MaFenetre extends JFrame implements KeyListener {
    @objid ("86cc6d52-afb6-4104-80f2-404d80b95580")
    private static final long serialVersionUID = 1L;

    @objid ("11ffa188-82b6-4d06-8a90-741d509ba440")
    private MonAfficheur monAfficheur; // Panneau d'affichage du jeu

    @objid ("874f95fe-2ef0-450d-96b6-32b60de5319b")
    private Controleur controleur; // Contrôleur du jeu

    @objid ("7eed2e19-5f3d-498d-b4de-88d07056d674")
    public MaFenetre(Controleur controleur) {
        super("Sokoban"); // Titre de la fenêtre
        this.controleur = controleur;
        initFrame(); // Initialise la fenêtre
        afficherEcranAccueil(); // Affiche l'écran d'accueil
    }

    @objid ("fcaed99f-0b29-4b1c-a1fe-353e78cd30a9")
    @Override
    public void keyPressed(KeyEvent e) {
        Direction direction = getDirectionFromKeyEvent(e); // Obtient la direction à partir de la touche pressée
        if (direction != null) {
            controleur.action(direction); // Déplace le gardien
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            controleur.reinitialiserNiveau(); // Réinitialise le niveau
        }
        monAfficheur.repaint(); // Rafraîchit l'affichage
        if (controleur.estTermine()) {
            handleNiveauTermine(); // Gère la fin du niveau
        }
    }

    @objid ("b23f72cb-f57e-43dd-8400-43e372fd62bc")
    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilisé
    }

    @objid ("2d2cb311-b58a-4d1a-b837-2515ef8b44c2")
    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilisé
    }

    @objid ("7a4c2417-3c1f-43f0-90b8-6a038c60e1c7")
    private Direction getDirectionFromKeyEvent(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: return Direction.HAUT; // Haut
            case KeyEvent.VK_DOWN: return Direction.BAS; // Bas
            case KeyEvent.VK_LEFT: return Direction.GAUCHE; // Gauche
            case KeyEvent.VK_RIGHT: return Direction.DROITE; // Droite
            default: return null; // Touche non reconnue
        }
    }

    @objid ("cdd4791d-b0c4-43e9-8049-0b90e729c97a")
    private void handleNiveauTermine() {
        if (controleur.estDernierNiveau()) {
            showMessage("Félicitations ! Vous avez terminé tous les niveaux !", "Jeu terminé"); // Message de fin de jeu
            System.exit(0); // Quitte le jeu
        } else {
            int choix = showConfirmDialog("Niveau terminé ! Voulez-vous passer au niveau suivant ?", "Niveau terminé");
            if (choix == JOptionPane.YES_OPTION) {
                controleur.passerAuNiveauSuivant(); // Passe au niveau suivant
            } else {
                handleRejouerOuQuitter(); // Propose de rejouer ou de quitter
            }
            monAfficheur.repaint(); // Rafraîchit l'affichage
        }
    }

    @objid ("f57679a2-33f2-442b-b1ce-d47211876a17")
    private void afficherEcranAccueil() {
        EcranAccueil ecranAccueil = new EcranAccueil(this); // Crée l'écran d'accueil
        getContentPane().removeAll(); // Vide le contenu de la fenêtre
        getContentPane().add(ecranAccueil); // Ajoute l'écran d'accueil
        revalidate(); // Rafraîchit la fenêtre
        repaint(); // Redessine la fenêtre
    }

    @objid ("e83371b1-e5ab-48e6-b01a-baab1257d9ee")
    public void demarrerJeu() {
        getContentPane().removeAll(); // Vide le contenu de la fenêtre
        this.monAfficheur = new MonAfficheur(controleur); // Crée le panneau d'affichage du jeu
        getContentPane().add(monAfficheur, BorderLayout.CENTER); // Ajoute le panneau au centre
        addKeyListener(this); // Ajoute un écouteur de touches
        setFocusable(true); // Permet à la fenêtre de recevoir les événements clavier
        requestFocusInWindow(); // Donne le focus à la fenêtre
        revalidate(); // Rafraîchit la fenêtre
        repaint(); // Redessine la fenêtre
    }

    @objid ("e3c723f4-54a6-40c9-ac6c-dcb28454c13b")
    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme l'application à la fermeture de la fenêtre
        setSize(800, 600); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centre la fenêtre
        setVisible(true); // Rend la fenêtre visible
    }

    @objid ("9210a491-070c-4381-ae03-2c64f0312008")
    private void handleRejouerOuQuitter() {
        Object[] options = { "Quitter", "Rejouer le niveau" }; // Options pour l'utilisateur
        int choix = showOptionDialog("Que souhaitez-vous faire ?", "Niveau terminé", options);
        if (choix == JOptionPane.YES_OPTION) {
            System.exit(0); // Quitte le jeu
        } else {
            controleur.reinitialiserNiveau(); // Rejoue le niveau
        }
    }

    @objid ("07dcc793-b0dd-40bf-88ca-fdf3773b13c0")
    private void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE); // Affiche un message
    }

    @objid ("298c30bb-c1b7-41e4-95fd-bdb2624cafc8")
    private int showConfirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION); // Affiche une boîte de dialogue de confirmation
    }

    @objid ("029c6009-96e7-47a4-b255-284107694170")
    private int showOptionDialog(String message, String title, Object[] options) {
        return JOptionPane.showOptionDialog(this, message, title, JOptionPane.YES_NO_OPTION,
                                                                                                                        JOptionPane.QUESTION_MESSAGE, null, options, options[1]); // Affiche une boîte de dialogue avec des options
    }

}
