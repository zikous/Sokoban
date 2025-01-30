package boundary;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.JFrame;
import control.Controleur;
import entity.Direction;

public class MaFenetre extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;

    private MonAfficheur monAfficheur; // Panneau d'affichage du jeu

    private Controleur controleur; // Contrôleur du jeu

    public MaFenetre(Controleur controleur) {
        super("Sokoban"); // Titre de la fenêtre
        this.controleur = controleur;
        initFrame(); // Initialise la fenêtre
        afficherEcranAccueil(); // Affiche l'écran d'accueil
    }

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

    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilisé
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilisé
    }

    private Direction getDirectionFromKeyEvent(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: return Direction.HAUT; // Haut
            case KeyEvent.VK_DOWN: return Direction.BAS; // Bas
            case KeyEvent.VK_LEFT: return Direction.GAUCHE; // Gauche
            case KeyEvent.VK_RIGHT: return Direction.DROITE; // Droite
            default: return null; // Touche non reconnue
        }
    }

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

    private void afficherEcranAccueil() {
        EcranAccueil ecranAccueil = new EcranAccueil(this); // Crée l'écran d'accueil
        getContentPane().removeAll(); // Vide le contenu de la fenêtre
        getContentPane().add(ecranAccueil); // Ajoute l'écran d'accueil
        revalidate(); // Rafraîchit la fenêtre
        repaint(); // Redessine la fenêtre
    }

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

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme l'application à la fermeture de la fenêtre
        setSize(800, 600); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centre la fenêtre
        setVisible(true); // Rend la fenêtre visible
    }

    private void handleRejouerOuQuitter() {
        Object[] options = { "Quitter", "Rejouer le niveau" }; // Options pour l'utilisateur
        int choix = showOptionDialog("Que souhaitez-vous faire ?", "Niveau terminé", options);
        if (choix == JOptionPane.YES_OPTION) {
            System.exit(0); // Quitte le jeu
        } else {
            controleur.reinitialiserNiveau(); // Rejoue le niveau
        }
    }

    private void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE); // Affiche un message
    }

    private int showConfirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION); // Affiche une boîte de dialogue de confirmation
    }

    private int showOptionDialog(String message, String title, Object[] options) {
        return JOptionPane.showOptionDialog(this, message, title, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, options[1]); // Affiche une boîte de dialogue avec des options
    }

}
