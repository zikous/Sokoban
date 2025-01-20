package boundary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import control.Controleur;
import entity.*;
import entity.Direction;

public class MaFenetre extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;

    private MonAfficheur monAfficheur;

    private Controleur controleur;

    public MaFenetre(Controleur controleur) {
        super("Sokoban");
        this.controleur = controleur;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        afficherEcranAccueil();
        setVisible(true);
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
            JOptionPane.showMessageDialog(this, "Félicitations ! Vous avez terminé tous les niveaux !",
                    "Jeu terminé", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else {
            int choix = JOptionPane.showConfirmDialog(this,
                    "Niveau terminé ! Voulez-vous passer au niveau suivant ?",
                    "Niveau terminé",
                    JOptionPane.YES_NO_OPTION);
            
            if (choix == JOptionPane.YES_OPTION) {
                controleur.passerAuNiveauSuivant();
            } else {
                Object[] options = { "Quitter", "Rejouer le niveau" };
                int choixQuitterOuRejouer = JOptionPane.showOptionDialog(this,
                        "Que souhaitez-vous faire ?",
                        "Niveau terminé",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);
            
                if (choixQuitterOuRejouer == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    controleur.reinitialiserNiveau();
                }
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
        
        JPanel panelCentre = new JPanel(new BorderLayout());
        panelCentre.setBackground(new Color(240, 240, 240));
        
        this.monAfficheur = new MonAfficheur(controleur);
        panelCentre.add(monAfficheur, BorderLayout.CENTER);
        
        getContentPane().add(panelCentre);
        
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
