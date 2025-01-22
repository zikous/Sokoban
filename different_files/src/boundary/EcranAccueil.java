package boundary;

import java.awt.*;
import javax.swing.*;

public class EcranAccueil extends PanneauAvecFond {
    private static final long serialVersionUID = 1L;

    private JButton boutonCommencer; // Bouton pour démarrer le jeu

    public EcranAccueil(MaFenetre fenetrePrincipale) {
        setLayout(new BorderLayout()); // Utilise un BorderLayout pour organiser les composants
        add(createTitre(), BorderLayout.NORTH); // Ajoute le titre en haut
        add(createPanelInfos(), BorderLayout.CENTER); // Ajoute les informations au centre
        add(createPanelBouton(fenetrePrincipale), BorderLayout.SOUTH); // Ajoute le bouton en bas
    }

    private JLabel createTitre() {
        JLabel titre = new JLabel("Sokoban", SwingConstants.CENTER); // Crée le titre
        titre.setFont(new Font("Roboto", Font.BOLD, 80)); // Définit la police
        titre.setForeground(Color.WHITE); // Définit la couleur du texte
        titre.setBorder(BorderFactory.createEmptyBorder(60, 0, 40, 0)); // Ajoute une marge
        return titre;
    }

    private JPanel createPanelInfos() {
        JPanel panelInfos = new JPanel();
        panelInfos.setLayout(new BoxLayout(panelInfos, BoxLayout.Y_AXIS)); // Utilise un BoxLayout vertical
        panelInfos.setOpaque(false); // Rend le panneau transparent
        panelInfos.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20)); // Ajoute une marge
        panelInfos.add(createCredits()); // Ajoute les crédits
        panelInfos.add(Box.createRigidArea(new Dimension(0, 30))); // Ajoute un espace
        panelInfos.add(createInstructions("Utilisez les flèches pour déplacer le gardien.")); // Ajoute les instructions
        panelInfos.add(Box.createRigidArea(new Dimension(0, 10))); // Ajoute un espace
        panelInfos.add(createInstructions("Appuyez sur 'R' pour réinitialiser le niveau.")); // Ajoute les instructions
        return panelInfos;
    }

    private JLabel createCredits() {
        JLabel credits = new JLabel("Fait par Zakaria Bheddar et Mohamed Cheikh Rouhou", SwingConstants.CENTER); // Crée les crédits
        credits.setFont(new Font("Roboto", Font.ITALIC, 20)); // Définit la police
        credits.setForeground(Color.WHITE); // Définit la couleur du texte
        credits.setAlignmentX(Component.CENTER_ALIGNMENT); // Centre le texte
        return credits;
    }

    private JLabel createInstructions(String text) {
        JLabel instructions = new JLabel(text, SwingConstants.CENTER); // Crée les instructions
        instructions.setFont(new Font("Roboto", Font.PLAIN, 18)); // Définit la police
        instructions.setForeground(Color.WHITE); // Définit la couleur du texte
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT); // Centre le texte
        return instructions;
    }

    private JPanel createPanelBouton(MaFenetre fenetrePrincipale) {
        JPanel panelBouton = new JPanel();
        panelBouton.setOpaque(false); // Rend le panneau transparent
        panelBouton.setBorder(BorderFactory.createEmptyBorder(30, 0, 60, 0)); // Ajoute une marge
        panelBouton.add(createBoutonCommencer(fenetrePrincipale)); // Ajoute le bouton "Commencer"
        return panelBouton;
    }

    private JButton createBoutonCommencer(MaFenetre fenetrePrincipale) {
        boutonCommencer = new JButton("Commencer"); // Crée le bouton "Commencer"
        boutonCommencer.setFont(new Font("Roboto", Font.BOLD, 24)); // Définit la police
        boutonCommencer.setBackground(new Color(33, 150, 243)); // Définit la couleur de fond
        boutonCommencer.setForeground(Color.WHITE); // Définit la couleur du texte
        boutonCommencer.setFocusPainted(false); // Désactive la bordure de focus
        boutonCommencer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(25, 118, 210), 3), // Ajoute une bordure
            BorderFactory.createEmptyBorder(15, 40, 15, 40) // Ajoute une marge interne
        ));
        boutonCommencer.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change le curseur au survol
        boutonCommencer.addActionListener(e -> fenetrePrincipale.demarrerJeu()); // Ajoute un écouteur pour démarrer le jeu
        
        // Effet de survol pour le bouton
        boutonCommencer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boutonCommencer.setBackground(new Color(25, 118, 210)); // Change la couleur au survol
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boutonCommencer.setBackground(new Color(33, 150, 243)); // Revert la couleur après le survol
            }
        });
        return boutonCommencer;
    }

}
