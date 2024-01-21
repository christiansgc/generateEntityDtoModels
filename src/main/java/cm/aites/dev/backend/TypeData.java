package cm.aites.dev.backend;

public enum TypeData {
    INTEGER("Integer"),
    DOUBLE("Double"),
    STRING("String"),
    STRING_LOB("String"),
    BOOLEAN("Boolean"),
    DATE("Date"),
    FLOAT("Float");
    private final String valeur;

    private TypeData(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return this.valeur;
    }
}
