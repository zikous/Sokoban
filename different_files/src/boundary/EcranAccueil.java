package boundary;

import java.awt.*;
import javax.swing.*;

public class EcranAccueil extends PanneauAvecFond {
    private static final long serialVersionUID = 1L;

    private JButton boutonCommencer;

    public EcranAccueil(MaFenetre fenetrePrincipale) {
        setLayout(new BorderLayout());
        add(createTitre(), BorderLayout.NORTH);
        add(createPanelInfos(), BorderLayout.CENTER);
        add(createPanelBouton(fenetrePrincipale), BorderLayout.SOUTH);
    }

    private JLabel createTitre() {
        JLabel titre = new JLabel("Sokoban", SwingConstants.CENTER);
        titre.setFont(new Font("Roboto", Font.BOLD, 80));
        titre.setForeground(Color.WHITE);
        titre.setBorder(BorderFactory.createEmptyBorder(60, 0, 40, 0));
        return titre;
    }

    private JPanel createPanelInfos() {
        JPanel panelInfos = new JPanel();
        panelInfos.setLayout(new BoxLayout(panelInfos, BoxLayout.Y_AXIS));
        panelInfos.setOpaque(false);
        panelInfos.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        panelInfos.add(createCredits());
        panelInfos.add(Box.createRigidArea(new Dimension(0, 30)));
        panelInfos.add(createInstructions("Utilisez les flèches pour déplacer le gardien."));
        panelInfos.add(Box.createRigidArea(new Dimension(0, 10)));
        panelInfos.add(createInstructions("Appuyez sur 'R' pour réinitialiser le niveau."));
        return panelInfos;
    }

    private JLabel createCredits() {
        JLabel credits = new JLabel("Fait par Zakaria Bheddar et Mohamed Cheikh Rouhou", SwingConstants.CENTER);
        credits.setFont(new Font("Roboto", Font.ITALIC, 20));
        credits.setForeground(Color.WHITE);
        credits.setAlignmentX(Component.CENTER_ALIGNMENT);
        return credits;
    }

    private JLabel createInstructions(String text) {
        JLabel instructions = new JLabel(text, SwingConstants.CENTER);
        instructions.setFont(new Font("Roboto", Font.PLAIN, 18));
        instructions.setForeground(Color.WHITE);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        return instructions;
    }

    private JPanel createPanelBouton(MaFenetre fenetrePrincipale) {
        JPanel panelBouton = new JPanel();
        panelBouton.setOpaque(false);
        panelBouton.setBorder(BorderFactory.createEmptyBorder(30, 0, 60, 0));
        panelBouton.add(createBoutonCommencer(fenetrePrincipale));
        return panelBouton;
    }

    private JButton createBoutonCommencer(MaFenetre fenetrePrincipale) {
        boutonCommencer = new JButton("Commencer");
        boutonCommencer.setFont(new Font("Roboto", Font.BOLD, 24));
        boutonCommencer.setBackground(new Color(33, 150, 243));
        boutonCommencer.setForeground(Color.WHITE);
        boutonCommencer.setFocusPainted(false);
        boutonCommencer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(25, 118, 210), 3),
            BorderFactory.createEmptyBorder(15, 40, 15, 40)
        ));
        boutonCommencer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boutonCommencer.addActionListener(e -> fenetrePrincipale.demarrerJeu());
        
        boutonCommencer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boutonCommencer.setBackground(new Color(25, 118, 210));
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boutonCommencer.setBackground(new Color(33, 150, 243));
            }
        });
        return boutonCommencer;
    }

}
