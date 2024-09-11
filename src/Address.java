import java.io.*;

// Serializable class with nested object
class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    private String street;
    private String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{street='" + street + "', city='" + city + "'}";
    }
}

class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Address address; // Nested Serializable object

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', address=" + address + "}";
    }

    public static void main(String[] args) {
        Address address = new Address("123 Main St", "Springfield");
        Person person = new Person("John Doe", address);

        // Serialize the object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("person_with_address.ser"))) {
            out.writeObject(person);
            System.out.println("Person object with address serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("person_with_address.ser"))) {
            Person deserializedPerson = (Person) in.readObject();
            System.out.println("Deserialized Person: " + deserializedPerson);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
