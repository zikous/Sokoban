package boundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("c46ba35e-38a7-4c65-a95d-ca39d70f960f")
public class EcranAccueil extends JPanel {
    @objid ("d2609eb5-9d21-4db0-a385-69454518e779")
    private static final long serialVersionUID = 1L;

    @objid ("8544744b-ddc3-408b-b068-d5a7c3c30e88")
    private JButton boutonCommencer;

    @objid ("c905e5f7-a676-4dc7-bc33-04fb86750d0f")
    public EcranAccueil(MaFenetre fenetrePrincipale) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245)); // Fond clair et moderne

        // Titre du jeu
        JLabel titre = new JLabel("Sokoban", SwingConstants.CENTER);
        titre.setFont(new Font("Roboto", Font.BOLD, 72)); // Police moderne
        titre.setForeground(new Color(33, 150, 243)); // Bleu vif
        titre.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        add(titre, BorderLayout.NORTH);

        // Panel pour les informations supplémentaires
        JPanel panelInfos = new JPanel();
        panelInfos.setLayout(new BoxLayout(panelInfos, BoxLayout.Y_AXIS));
        panelInfos.setBackground(new Color(245, 245, 245)); // Fond clair
        panelInfos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crédits
        JLabel credits = new JLabel("Fait par Zakaria Bheddar et Mohamed Cheikh Rouhou", SwingConstants.CENTER);
        credits.setFont(new Font("Roboto", Font.ITALIC, 18));
        credits.setForeground(new Color(97, 97, 97)); // Gris foncé
        credits.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfos.add(credits);

        // Espacement
        panelInfos.add(Box.createRigidArea(new Dimension(0, 20)));

        // Instructions
        JLabel instructions = new JLabel("Utilisez les flèches pour déplacer le gardien.", SwingConstants.CENTER);
        instructions.setFont(new Font("Roboto", Font.PLAIN, 16));
        instructions.setForeground(new Color(33, 33, 33)); // Noir doux
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfos.add(instructions);

        JLabel instructions2 = new JLabel("Appuyez sur 'R' pour réinitialiser le niveau.", SwingConstants.CENTER);
        instructions2.setFont(new Font("Roboto", Font.PLAIN, 16));
        instructions2.setForeground(new Color(33, 33, 33)); // Noir doux
        instructions2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfos.add(instructions2);

        add(panelInfos, BorderLayout.CENTER);

        // Bouton pour commencer
        boutonCommencer = new JButton("Commencer");
        boutonCommencer.setFont(new Font("Roboto", Font.BOLD, 20));
        boutonCommencer.setBackground(new Color(33, 150, 243)); // Bleu vif
        boutonCommencer.setForeground(Color.WHITE);
        boutonCommencer.setFocusPainted(false);
        boutonCommencer.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        boutonCommencer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(25, 118, 210), 2), // Bordure bleue
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        boutonCommencer.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Curseur en forme de main
        boutonCommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetrePrincipale.demarrerJeu();
            }
        });

        // Effet de survol sur le bouton
        boutonCommencer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boutonCommencer.setBackground(new Color(25, 118, 210)); // Bleu plus foncé au survol
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boutonCommencer.setBackground(new Color(33, 150, 243)); // Retour à la couleur d'origine
            }
        });

        // Ajouter une ombre au bouton
        JPanel panelBouton = new JPanel();
        panelBouton.setBackground(new Color(245, 245, 245)); // Fond clair
        panelBouton.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));
        panelBouton.add(boutonCommencer);
        add(panelBouton, BorderLayout.SOUTH);
    }
}