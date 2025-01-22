package entity;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7a4676f4-ea89-40ed-b5f6-2561c23dec7a")
public class Entrepot {
    @objid ("4310f7f9-a42f-4231-9f22-c3fb5a313aaa")
    private List<Position> positions = new ArrayList<>(); // Positions disponibles

    @objid ("de67d322-7095-427b-a023-70a20df7efd9")
    private List<Zone> zones = new ArrayList<>(); // Zones associées

    @objid ("f61e1b54-c2fa-4667-80af-7585fe22abed")
    public void initialiser(int lignes, int colonnes) {
        positions.clear();
        zones.clear();
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                Position position = new Position(i, j, this);
                Zone zone = new Zone(position);
                positions.add(position);
                zones.add(zone);
            }
        }
    }

    @objid ("0a73f4c1-bf9e-437d-a920-aaa2eb5f423d")
    public Position getPosition(int ligne, int colonne) {
        for (Position position : positions) {
            if (position.getLigne() == ligne && position.getColonne() == colonne) {
                return position;
            }
        }
        return null; // Position non trouvée
    }

    @objid ("f13a0e36-f7c2-4c4b-a599-1805ba2a754d")
    public boolean estTermine() {
        return zones.stream().filter(Zone::isEstCible).allMatch(Zone::estComplete);
    }

    @objid ("c845fdf8-b9ad-41cd-ad0f-a6ab9832ce3f")
    public void clear() {
        for (Zone zone : zones) {
            zone.setMobile(null);
            zone.setEstMur(false);
            zone.setEstCible(false);
        }
    }

    @objid ("5b957880-a0b6-4a13-8f9c-ce11d7cb8309")
    public void placerElement(int ligne, int colonne, TypeElement type) {
        Position position = getPosition(ligne, colonne);
        if (position == null) return;
        
        Zone zone = position.getZone();
        switch (type) {
            case MUR:
                zone.setEstMur(true);
                break;
            case CIBLE:
                zone.setEstCible(true);
                break;
            case CAISSE:
                if (zone.estLibre()) {
                    new Caisse().setZone(zone);
                }
                break;
            default:
                throw new IllegalArgumentException("Type non géré : " + type);
        }
    }

    @objid ("8b75d7bc-3993-4852-b63e-998a78017c93")
    @Override
    public String toString() {
        int maxLigne = -1;
        int maxColonne = -1;
        for (Position pos : positions) {
            maxLigne = Math.max(maxLigne, pos.getLigne());
            maxColonne = Math.max(maxColonne, pos.getColonne());
        }
        
        String result = "";
        for (int i = 0; i <= maxLigne; i++) {
            for (int j = 0; j <= maxColonne; j++) {
                Position pos = getPosition(i, j);
                if (pos == null) {
                    result += "  ";
                    continue;
                }
                
                Zone zone = pos.getZone();
                char symbole = ' ';
                
                if (zone.isEstMur()) {
                    symbole = TypeElement.MUR.getSymbol();
                } else if (zone.getMobile() instanceof Gardien) {
                    symbole = zone.isEstCible() ? TypeElement.GARDIEN_CIBLE.getSymbol() : TypeElement.GARDIEN.getSymbol();
                } else if (zone.contientCaisse()) {
                    symbole = zone.isEstCible() ? TypeElement.CIBLE_CAISSE.getSymbol() : TypeElement.CAISSE.getSymbol();
                } else if (zone.isEstCible()) {
                    symbole = TypeElement.CIBLE.getSymbol();
                }
                
                result += symbole + " ";
            }
            result += "\n";
        }
        return result;
    }

}
