import java.io.*;

class Animal implements Serializable {
    private static final long serialVersionUID = 1L;
    private String species;

    public Animal(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "Animal{species='" + species + "'}";
    }
}

class Dog extends Animal {
    private static final long serialVersionUID = 1L;
    private String name;

    public Dog(String species, String name) {
        super(species);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{name='" + name + "', species='" + super.toString() + "'}";
    }

    public static void main(String[] args) {
        Dog dog = new Dog("Canine", "Buddy");

        // Serialize the object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("dog.ser"))) {
            out.writeObject(dog);
            System.out.println("Dog object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("dog.ser"))) {
            Dog deserializedDog = (Dog) in.readObject();
            System.out.println("Deserialized Dog: " + deserializedDog);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
