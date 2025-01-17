package entity;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7a4676f4-ea89-40ed-b5f6-2561c23dec7a")
public class Entrepot {
    @objid ("4310f7f9-a42f-4231-9f22-c3fb5a313aaa")
    private List<Position> positions = new ArrayList<>();

    @objid ("de67d322-7095-427b-a023-70a20df7efd9")
    private List<Zone> zones = new ArrayList<>();

    @objid ("9ddd3eb1-b3cc-42e3-a42d-b67786b0044c")
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

    @objid ("7468f5a2-4bbc-47ba-bc0e-54933163df92")
    public Position getPosition(int ligne, int colonne) {
        for (Position position : positions) {
            if (position.getLigne() == ligne && position.getColonne() == colonne) {
                return position;
            }
        }
        return null;
    }

    @objid ("7a0cd4ae-1ffa-470d-aae2-e6ebba6ace44")
    public boolean estTermine() {
        return zones.stream()
                                        .filter(Zone::isEstCible)
                                        .allMatch(Zone::estComplete);
    }

    @objid ("fdb8a6de-2e02-4763-8477-c3626f258a52")
    public void clear() {
        for (Zone zone : zones) {
            zone.setMobile(null);
            zone.setEstMur(false);
            zone.setEstCible(false);
        }
    }

    @objid ("263b23fd-11b0-4738-bbbd-17ade85eb29a")
    public void placerElement(int ligne, int colonne, TypeElement type) {
        Position position = getPosition(ligne, colonne);
        if (position == null)
            return;
        
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
                throw new IllegalArgumentException("Type d'élément non géré : " + type);
        }
    }

}
