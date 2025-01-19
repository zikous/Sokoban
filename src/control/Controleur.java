package control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import entity.*;
import entity.Direction;
import entity.Entrepot;
import entity.Gardien;

@objid ("14f91e90-d328-4cde-b2ef-ec5fcc074d37")
public class Controleur {
    @objid ("8e85fa44-a2b6-49fe-ac47-6d4564d03f31")
    private int[][] niveauInitial;

    @objid ("eae08b9d-694a-4850-886b-2e97debff8ac")
    private Gardien gardien;

    @objid ("54da0ef2-d468-468a-9149-9b67e21c4ff3")
    private Entrepot entrepot;

    @objid ("afb21de7-564d-4f9a-a5ad-c1b8fb64f3dd")
    private NiveauManager niveauManager;

    @objid ("b4eb871f-b7e0-471c-a89a-31c91670ec60")
    public void action(Direction direction) {
        if (!entrepot.estTermine() && gardien.getZone() != null) {
            gardien.deplacer(direction);
        }
    }

    @objid ("34fea563-bd44-44cd-a263-50bf97abbb46")
    public Controleur() {
        this.entrepot = new Entrepot();
        this.gardien = new Gardien();
        this.niveauManager = new NiveauManager();
        chargerNiveauCourant();
    }

    @objid ("440fe7ef-4b0e-4f82-9add-be2866973951")
    public void chargerNiveau(int[][] niveau) {
        this.niveauInitial = new int[niveau.length][niveau[0].length];
        for (int i = 0; i < niveau.length; i++) {
            System.arraycopy(niveau[i], 0, niveauInitial[i], 0, niveau[i].length);
        }
        
        entrepot.clear();
        entrepot.initialiser(niveau.length, niveau[0].length);
        
        for (int i = 0; i < niveau.length; i++) {
            for (int j = 0; j < niveau[0].length; j++) {
                switch (niveau[i][j]) {
                    case 1: 
                        entrepot.placerElement(i, j, TypeElement.MUR);
                        break;
                    case 2: 
                        entrepot.placerElement(i, j, TypeElement.CIBLE);
                        break;
                    case 3: 
                        entrepot.placerElement(i, j, TypeElement.CAISSE);
                        break;
                    case 4: 
                        Position pos = entrepot.getPosition(i, j);
                        if (pos != null && pos.getZone().estLibre()) {
                            gardien.setZone(pos.getZone());
                        }
                        break;
                    case 5: 
                        entrepot.placerElement(i, j, TypeElement.CIBLE);
                        entrepot.placerElement(i, j, TypeElement.CAISSE);
                        break;
                    case 6: 
                        Position posGardien = entrepot.getPosition(i, j);
                        if (posGardien != null && posGardien.getZone().estLibre()) {
                            gardien.setZone(posGardien.getZone());
                        }
                        entrepot.placerElement(i, j, TypeElement.CIBLE);
                        break;
                }
            }
        }
    }

    @objid ("74fc1f4b-efe9-40a8-86c8-fc7b99940210")
    public boolean estTermine() {
        return entrepot.estTermine();
    }

    @objid ("b87f4879-3f4d-4ee3-9689-fb9edf4701fa")
    public void reinitialiserNiveau() {
        if (niveauInitial != null) {
            chargerNiveau(niveauInitial);
        }
    }

    @objid ("45631f7d-3f27-4f99-9d77-e53a04a3392b")
    public Entrepot getEntrepot() {
        return entrepot;
    }

    @objid ("ef9a3c18-f8bc-4723-bd77-7fae01caf358")
    public Gardien getGardien() {
        return gardien;
    }

    @objid ("15e80bed-8b08-4b8b-af92-9f17e3c1969b")
    private void chargerNiveauCourant() {
        chargerNiveau(niveauManager.getNiveauCourant());
    }

    @objid ("31eb1263-8853-489d-af0b-63a1500d0a29")
    public boolean estDernierNiveau() {
        return niveauManager.estDernierNiveau();
    }

    @objid ("b5a95c97-2880-40c0-9d30-f32d7e4b5ed1")
    public void passerAuNiveauSuivant() {
        if (niveauManager.passerAuNiveauSuivant()) {
            chargerNiveauCourant();
        }
    }

}
