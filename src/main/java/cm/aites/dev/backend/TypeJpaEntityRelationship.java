package cm.aites.dev.backend;

public enum TypeJpaEntityRelationship {
    MANYTOONE("ManyToOne"),
    ONETOMANY("OneToMany"),
    ONETOONE("OneToOne"),
    MANYTOMANY("ManyToMany");
    private final String valeur;

    private TypeJpaEntityRelationship(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return this.valeur;
    }
}
