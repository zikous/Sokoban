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
        setBackground(new Color(240, 240, 240));
        
        // Titre du jeu
        JLabel titre = new JLabel("Sokoban", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 64));
        titre.setForeground(new Color(30, 144, 255));
        titre.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        add(titre, BorderLayout.NORTH);
        
        // Panel pour les informations supplémentaires
        JPanel panelInfos = new JPanel();
        panelInfos.setLayout(new BoxLayout(panelInfos, BoxLayout.Y_AXIS));
        panelInfos.setBackground(new Color(240, 240, 240));
        
        // Crédits
        JLabel credits = new JLabel("Fait par Zakaria Bheddar et Mohamed Cheikh Rouhou", SwingConstants.CENTER);
        credits.setFont(new Font("Arial", Font.ITALIC, 18));
        credits.setForeground(new Color(100, 100, 100));
        credits.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfos.add(credits);
        
        // Espacement
        panelInfos.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Instructions
        JLabel instructions = new JLabel("Utilisez les flèches pour déplacer le gardien.", SwingConstants.CENTER);
        instructions.setFont(new Font("Arial", Font.PLAIN, 16));
        instructions.setForeground(new Color(50, 50, 50));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfos.add(instructions);
        
        JLabel instructions2 = new JLabel("Appuyez sur 'R' pour réinitialiser le niveau.", SwingConstants.CENTER);
        instructions2.setFont(new Font("Arial", Font.PLAIN, 16));
        instructions2.setForeground(new Color(50, 50, 50));
        instructions2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfos.add(instructions2);
        
        add(panelInfos, BorderLayout.CENTER);
        
        // Bouton pour commencer
        boutonCommencer = new JButton("Commencer");
        boutonCommencer.setFont(new Font("Arial", Font.PLAIN, 24));
        boutonCommencer.setBackground(new Color(30, 144, 255));
        boutonCommencer.setForeground(Color.WHITE);
        boutonCommencer.setFocusPainted(false);
        boutonCommencer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boutonCommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetrePrincipale.demarrerJeu();
            }
        });
        
        JPanel panelBouton = new JPanel();
        panelBouton.setBackground(new Color(240, 240, 240));
        panelBouton.add(boutonCommencer);
        add(panelBouton, BorderLayout.SOUTH);
    }

}
