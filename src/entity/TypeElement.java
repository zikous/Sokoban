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

    @objid ("dbd65b00-7438-4401-8a75-51654c1c2805")
    private final char symbol;

    @objid ("d6b177be-bdd8-44e9-8284-be8ca8bfb0f7")
    TypeElement(char symbol) {
        this.symbol = symbol;
    }

    @objid ("2ca78895-9424-44ce-ba33-69eb28844ab3")
    public char getSymbol() {
        return symbol;
    }

    @objid ("e7f8f8b5-cbd4-43f1-9a52-6076e41800f6")
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
