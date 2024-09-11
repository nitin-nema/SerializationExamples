import java.io.*;

public class CustomSerializable implements Serializable {
    private static final long serialVersionUID = 1L;
    private String data;

    public CustomSerializable(String data) {
        this.data = data;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Write default fields
        out.writeObject("Custom data: " + data); // Write custom data
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Read default fields
        String customData = (String) in.readObject(); // Read custom data
        data = customData.replace("Custom data: ", ""); // Process custom data
    }

    @Override
    public String toString() {
        return "CustomSerializable{data='" + data + "'}";
    }

    public static void main(String[] args) {
        CustomSerializable original = new CustomSerializable("Hello World");

        // Serialize the object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("custom.ser"))) {
            out.writeObject(original);
            System.out.println("CustomSerializable object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("custom.ser"))) {
            CustomSerializable deserialized = (CustomSerializable) in.readObject();
            System.out.println("Deserialized CustomSerializable: " + deserialized);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
