import java.io.*;

class NonSerializableClass {
    // This class does not implement Serializable
}

class Wrapper implements Serializable {
    private static final long serialVersionUID = 1L;
    private NonSerializableClass nonSerializableField;

    public Wrapper(NonSerializableClass nonSerializableField) {
        this.nonSerializableField = nonSerializableField;
    }

    public static void main(String[] args) {
        NonSerializableClass nonSerializable = new NonSerializableClass();
        Wrapper wrapper = new Wrapper(nonSerializable);

        // Serialize the object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("wrapper.ser"))) {
            out.writeObject(wrapper);
            System.out.println("Wrapper object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
