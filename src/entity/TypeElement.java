package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("026988a7-013c-403c-a50f-0fe1f32038e7")
public enum TypeElement {
    MUR ('#'), // Mur
    CIBLE ('.'), // Cible
    CAISSE ('$'), // Caisse
    GARDIEN ('@'), // Gardien
    CIBLE_CAISSE ('*'), // Caisse sur cible
    GARDIEN_CIBLE ('+'); // Gardien sur cible

    @objid ("8401284f-0d99-4db8-8436-484894babb65")
    private final char symbol;

    @objid ("5d05f70e-cb84-4816-a8d8-847896fbbb97")
    TypeElement(char symbol) {
        this.symbol = symbol;
    }

    @objid ("5eae3d32-4aa9-40e2-a65f-05697a1d30fb")
    public char getSymbol() {
        return symbol;
    }

    @objid ("caa70238-4db6-4d8f-bbb1-efbbfb4bbd9d")
    public static TypeElement fromInt(int value) {
        switch (value) {
            case 1: return MUR;
            case 2: return CIBLE;
            case 3: return CAISSE;
            case 4: return GARDIEN;
            case 5: return CIBLE_CAISSE;
            case 6: return GARDIEN_CIBLE;
            default: throw new IllegalArgumentException("Valeur invalide pour TypeElement : " + value);
        }
    }

}
