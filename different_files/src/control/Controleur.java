package control;

import entity.*;
import entity.Direction;
import entity.Entrepot;
import entity.Gardien;

public class Controleur {
    private int[][] niveauInitial;

    private Gardien gardien;

    private Entrepot entrepot;

    private NiveauManager niveauManager;

    public void action(Direction direction) {
        if (!entrepot.estTermine() && gardien.getZone() != null) {
            gardien.deplacer(direction);
        }
    }

    public Controleur() {
        this.entrepot = new Entrepot();
        this.gardien = new Gardien();
        this.niveauManager = new NiveauManager();
        chargerNiveauCourant();
    }

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

    public boolean estTermine() {
        return entrepot.estTermine();
    }

    public void reinitialiserNiveau() {
        if (niveauInitial != null) {
            chargerNiveau(niveauInitial);
        }
    }

    public Entrepot getEntrepot() {
        return entrepot;
    }

    public Gardien getGardien() {
        return gardien;
    }

    private void chargerNiveauCourant() {
        chargerNiveau(niveauManager.getNiveauCourant());
    }

    public boolean estDernierNiveau() {
        return niveauManager.estDernierNiveau();
    }

    public void passerAuNiveauSuivant() {
        if (niveauManager.passerAuNiveauSuivant()) {
            chargerNiveauCourant();
        }
    }

}
