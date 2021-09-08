public class Animal {
    private final String name;
    private final String kingdom;
    private final String phylum; //тип (хордовые, например)
    private final String animalClass;
    private final String order; // отряд
    private final String family;
    private final String genus; // род
    private final String species; // вид
    private final String link; // ссылка на вики с этим животным

    public Animal(String name, String kingdom, String phylum, String animalClass, String order, String family, String genus, String species, String link) {
        this.name = name;
        this.kingdom = kingdom.substring(9);
        this.phylum = phylum.substring(5);
        this.animalClass = animalClass.substring(7);
        this.order = order.substring(7);
        this.family = family.substring(11);
        this.genus = genus.substring(5);
        this.species = species.substring(5);
        this.link = link;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", kingdom='" + kingdom + '\'' +
                ", phylum='" + phylum + '\'' +
                ", animalClass='" + animalClass + '\'' +
                ", order='" + order + '\'' +
                ", family='" + family + '\'' +
                ", genus='" + genus + '\'' +
                ", species='" + species + '\'' +
                '}';
    }
}
