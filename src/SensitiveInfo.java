import java.io.*;

class SensitiveInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sensitiveData;

    public SensitiveInfo(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    @Override
    public String toString() {
        return "SensitiveInfo{[REDACTED]}"; // Avoid displaying sensitive data
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // Serialize in a secure manner
        out.writeObject(encrypt(sensitiveData));
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // Deserialize in a secure manner
        String encryptedData = (String) in.readObject();
        sensitiveData = decrypt(encryptedData);
    }

    private String encrypt(String data) {
        // Simple encryption logic
        return new StringBuilder(data).reverse().toString();
    }

    private String decrypt(String data) {
        // Simple decryption logic
        return new StringBuilder(data).reverse().toString();
    }

    public static void main(String[] args) {
        SensitiveInfo sensitiveInfo = new SensitiveInfo("Secret123");

        // Serialize the object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("sensitive_info.ser"))) {
            out.writeObject(sensitiveInfo);
            System.out.println("SensitiveInfo object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("sensitive_info.ser"))) {
            SensitiveInfo deserializedInfo = (SensitiveInfo) in.readObject();
            System.out.println("Deserialized SensitiveInfo: " + deserializedInfo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
