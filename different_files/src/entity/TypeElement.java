package entity;


public enum TypeElement {
    MUR ('#'), // Mur
    CIBLE ('.'), // Cible
    CAISSE ('$'), // Caisse
    GARDIEN ('@'), // Gardien
    CIBLE_CAISSE ('*'), // Caisse sur cible
    GARDIEN_CIBLE ('+'); // Gardien sur cible

    private final char symbol;

    TypeElement(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

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
