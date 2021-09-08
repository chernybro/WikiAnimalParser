import java.lang.reflect.Array;
import java.util.ArrayList;

public class AnimalSet {
    private ArrayList<Animal> animals;

    public AnimalSet(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }
}
