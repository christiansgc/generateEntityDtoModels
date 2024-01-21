package cm.aites.dev.backend;

public enum TypeEntityDto {
    TABLE("table"),
    VIEW("view");
    private final String valeur;

    private TypeEntityDto(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return this.valeur;
    }
}
