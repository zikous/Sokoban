package boundary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import control.Controleur;
import entity.Direction;

public class MaFenetre extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;

    private MonAfficheur monAfficheur;

    private Controleur controleur;

    public MaFenetre(Controleur controleur) {
        super("Sokoban");
        this.controleur = controleur;
        initFrame();
        afficherEcranAccueil();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Direction direction = getDirectionFromKeyEvent(e);
        if (direction != null) {
            controleur.action(direction);
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            controleur.reinitialiserNiveau();
        }
        monAfficheur.repaint();
        if (controleur.estTermine()) {
            handleNiveauTermine();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private Direction getDirectionFromKeyEvent(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: return Direction.HAUT;
            case KeyEvent.VK_DOWN: return Direction.BAS;
            case KeyEvent.VK_LEFT: return Direction.GAUCHE;
            case KeyEvent.VK_RIGHT: return Direction.DROITE;
            default: return null;
        }
    }

    private void handleNiveauTermine() {
        if (controleur.estDernierNiveau()) {
            showMessage("Félicitations ! Vous avez terminé tous les niveaux !", "Jeu terminé");
            System.exit(0);
        } else {
            int choix = showConfirmDialog("Niveau terminé ! Voulez-vous passer au niveau suivant ?", "Niveau terminé");
            if (choix == JOptionPane.YES_OPTION) {
                controleur.passerAuNiveauSuivant();
            } else {
                handleRejouerOuQuitter();
            }
            monAfficheur.repaint();
        }
    }

    private void afficherEcranAccueil() {
        EcranAccueil ecranAccueil = new EcranAccueil(this);
        getContentPane().removeAll();
        getContentPane().add(ecranAccueil);
        revalidate();
        repaint();
    }

    public void demarrerJeu() {
        getContentPane().removeAll();
        this.monAfficheur = new MonAfficheur(controleur);
        getContentPane().add(monAfficheur, BorderLayout.CENTER);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        revalidate();
        repaint();
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleRejouerOuQuitter() {
        Object[] options = { "Quitter", "Rejouer le niveau" };
        int choix = showOptionDialog("Que souhaitez-vous faire ?", "Niveau terminé", options);
        if (choix == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            controleur.reinitialiserNiveau();
        }
    }

    private void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private int showConfirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
    }

    private int showOptionDialog(String message, String title, Object[] options) {
        return JOptionPane.showOptionDialog(this, message, title, JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
    }

}
