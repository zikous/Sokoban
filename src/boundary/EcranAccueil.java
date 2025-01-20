package boundary;

import java.awt.*;
import javax.swing.*;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d90c584a-242a-4be9-93e0-7080ac40e14c")
public class EcranAccueil extends PanneauAvecFond {
    @objid ("82cc66d1-83a6-4733-ae5f-f4405f8db157")
    private static final long serialVersionUID = 1L;

    @objid ("c8d13d18-9c13-4111-b045-e7e2fd9957fd")
    private JButton boutonCommencer;

    @objid ("03cff891-69e0-400c-8502-593f133bceba")
    public EcranAccueil(MaFenetre fenetrePrincipale) {
        setLayout(new BorderLayout());
        add(createTitre(), BorderLayout.NORTH);
        add(createPanelInfos(), BorderLayout.CENTER);
        add(createPanelBouton(fenetrePrincipale), BorderLayout.SOUTH);
    }

    @objid ("09dd40b8-4668-44fa-8f7d-205d94d3e7b2")
    private JLabel createTitre() {
        JLabel titre = new JLabel("Sokoban", SwingConstants.CENTER);
        titre.setFont(new Font("Roboto", Font.BOLD, 80)); // Taille augmentée pour plus d'impact
        titre.setForeground(new Color(255, 255, 255)); // Blanc pur
        titre.setBorder(BorderFactory.createEmptyBorder(60, 0, 40, 0)); // Espacement ajusté
        return titre;
    }

    @objid ("fe64f8d5-b318-42ab-b49b-ccda9d74e073")
    private JPanel createPanelInfos() {
        JPanel panelInfos = new JPanel();
        panelInfos.setLayout(new BoxLayout(panelInfos, BoxLayout.Y_AXIS));
        panelInfos.setOpaque(false); // Transparent pour laisser voir le fond
        panelInfos.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20)); // Espacement ajusté
        
        panelInfos.add(createCredits());
        panelInfos.add(Box.createRigidArea(new Dimension(0, 30))); // Espacement augmenté
        panelInfos.add(createInstructions("Utilisez les flèches pour déplacer le gardien."));
        panelInfos.add(Box.createRigidArea(new Dimension(0, 10))); // Espacement entre les instructions
        panelInfos.add(createInstructions("Appuyez sur 'R' pour réinitialiser le niveau."));
        return panelInfos;
    }

    @objid ("85b84feb-79e0-4d7c-8c7a-51e56c164e22")
    private JLabel createCredits() {
        JLabel credits = new JLabel("Fait par Zakaria Bheddar et Mohamed Cheikh Rouhou", SwingConstants.CENTER);
        credits.setFont(new Font("Roboto", Font.ITALIC, 20)); // Taille augmentée pour plus de visibilité
        credits.setForeground(new Color(255, 255, 255)); // Blanc pur
        credits.setAlignmentX(Component.CENTER_ALIGNMENT);
        return credits;
    }

    @objid ("84e42052-b96a-4ed9-beb1-2a468dcfccb5")
    private JLabel createInstructions(String text) {
        JLabel instructions = new JLabel(text, SwingConstants.CENTER);
        instructions.setFont(new Font("Roboto", Font.PLAIN, 18)); // Taille augmentée pour une meilleure lisibilité
        instructions.setForeground(new Color(255, 255, 255)); // Blanc pur
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        return instructions;
    }

    @objid ("713fb87d-8122-4280-a34c-61516bd6a0fb")
    private JPanel createPanelBouton(MaFenetre fenetrePrincipale) {
        JPanel panelBouton = new JPanel();
        panelBouton.setOpaque(false); // Transparent pour laisser voir le fond
        panelBouton.setBorder(BorderFactory.createEmptyBorder(30, 0, 60, 0)); // Espacement ajusté
        panelBouton.add(createBoutonCommencer(fenetrePrincipale));
        return panelBouton;
    }

    @objid ("704b68fb-d6ce-425d-a7d9-fa1392796d92")
    private JButton createBoutonCommencer(MaFenetre fenetrePrincipale) {
        boutonCommencer = new JButton("Commencer");
        boutonCommencer.setFont(new Font("Roboto", Font.BOLD, 24)); // Taille augmentée pour plus d'impact
        boutonCommencer.setBackground(new Color(33, 150, 243)); // Bleu vif
        boutonCommencer.setForeground(Color.WHITE); // Texte blanc
        boutonCommencer.setFocusPainted(false); // Supprime le contour de focus
        boutonCommencer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(25, 118, 210), 3), // Bordure plus épaisse
            BorderFactory.createEmptyBorder(15, 40, 15, 40) // Padding augmenté
        ));
        boutonCommencer.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Curseur en forme de main
        boutonCommencer.addActionListener(e -> fenetrePrincipale.demarrerJeu());
        
        // Effet de survol
        boutonCommencer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boutonCommencer.setBackground(new Color(25, 118, 210)); // Bleu plus foncé au survol
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boutonCommencer.setBackground(new Color(33, 150, 243)); // Retour à la couleur d'origine
            }
        });
        return boutonCommencer;
    }

}
