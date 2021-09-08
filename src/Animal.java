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
        if (kingdom.equals("undefined")) this.kingdom = kingdom; else this.kingdom = kingdom.substring(9);
        if (phylum.equals("undefined")) this.phylum = phylum; else this.phylum = phylum.substring(5);
        if (animalClass.equals("undefined")) this.animalClass = animalClass; else this.animalClass = animalClass.substring(7);
        if (order.equals("undefined")) this.order = order; else this.order = order.substring(7);
        if (family.equals("undefined")) this.family = family; else this.family = family.substring(11);
        if (genus.equals("undefined")) this.genus = genus; else this.genus = genus.substring(5);
        if (species.equals("undefined")) this.species = species; else this.species = species.substring(5);
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
