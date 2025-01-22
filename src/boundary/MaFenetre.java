package boundary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controleur;
import entity.Direction;

@objid ("2c98f262-fb7c-420e-8e27-d7b44a78c0dc")
public class MaFenetre extends JFrame implements KeyListener {
    @objid ("13aca0bc-7152-4285-9171-a1d2c2c1a376")
    private static final long serialVersionUID = 1L;

    @objid ("11ffa188-82b6-4d06-8a90-741d509ba440")
    private MonAfficheur monAfficheur; // Panneau d'affichage du jeu

    @objid ("874f95fe-2ef0-450d-96b6-32b60de5319b")
    private Controleur controleur; // Contrôleur du jeu

    @objid ("4dc4b2fe-2142-4d76-9dd9-bda64da17297")
    public MaFenetre(Controleur controleur) {
        super("Sokoban"); // Titre de la fenêtre
        this.controleur = controleur;
        initFrame(); // Initialise la fenêtre
        afficherEcranAccueil(); // Affiche l'écran d'accueil
    }

    @objid ("682aa214-9b02-4dff-bdb9-8ba4997aba72")
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

    @objid ("6d546574-dca0-4213-8bd4-35407aead6a9")
    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilisé
    }

    @objid ("d796e4a7-3eed-4144-9060-a4eb1e368c01")
    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilisé
    }

    @objid ("576d5ee2-3ee6-4ba4-b6b1-15328f6227fb")
    private Direction getDirectionFromKeyEvent(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: return Direction.HAUT; // Haut
            case KeyEvent.VK_DOWN: return Direction.BAS; // Bas
            case KeyEvent.VK_LEFT: return Direction.GAUCHE; // Gauche
            case KeyEvent.VK_RIGHT: return Direction.DROITE; // Droite
            default: return null; // Touche non reconnue
        }
    }

    @objid ("9696e72f-0a60-4834-8cf4-a095d30a5115")
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

    @objid ("86f6bddc-edb6-4480-be7c-08793c3fece8")
    private void afficherEcranAccueil() {
        EcranAccueil ecranAccueil = new EcranAccueil(this); // Crée l'écran d'accueil
        getContentPane().removeAll(); // Vide le contenu de la fenêtre
        getContentPane().add(ecranAccueil); // Ajoute l'écran d'accueil
        revalidate(); // Rafraîchit la fenêtre
        repaint(); // Redessine la fenêtre
    }

    @objid ("79cb8945-73dc-434d-957c-f7834ff1e553")
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

    @objid ("503a0644-dd0d-40de-82d6-5000b0e9f108")
    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme l'application à la fermeture de la fenêtre
        setSize(800, 600); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centre la fenêtre
        setVisible(true); // Rend la fenêtre visible
    }

    @objid ("13e70de3-1c43-4016-b544-0a21f99d3c67")
    private void handleRejouerOuQuitter() {
        Object[] options = { "Quitter", "Rejouer le niveau" }; // Options pour l'utilisateur
        int choix = showOptionDialog("Que souhaitez-vous faire ?", "Niveau terminé", options);
        if (choix == JOptionPane.YES_OPTION) {
            System.exit(0); // Quitte le jeu
        } else {
            controleur.reinitialiserNiveau(); // Rejoue le niveau
        }
    }

    @objid ("43f357ee-5594-478b-81d0-280dbd75e4e6")
    private void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE); // Affiche un message
    }

    @objid ("3230e794-9524-430a-8177-2ee64fbd9ae0")
    private int showConfirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION); // Affiche une boîte de dialogue de confirmation
    }

    @objid ("d1883b27-b3cc-4eb7-ba31-42a244d6da0e")
    private int showOptionDialog(String message, String title, Object[] options) {
        return JOptionPane.showOptionDialog(this, message, title, JOptionPane.YES_NO_OPTION,
                                                                                        JOptionPane.QUESTION_MESSAGE, null, options, options[1]); // Affiche une boîte de dialogue avec des options
    }

}
