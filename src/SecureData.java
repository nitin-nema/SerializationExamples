import java.io.*;

class SecureData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sensitiveData;

    public SecureData(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    // Custom serialization or cpmntrol
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();  //handle the default serialisation of non-transient field
        String encryptedData = encrypt(sensitiveData);
        out.writeObject(encryptedData); // serialiesed
    }

    // Custom deserialization
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        String encryptedData = (String) in.readObject();
        sensitiveData = decrypt(encryptedData);
    }

    //writer replace
    private Object writeReplace() throws ObjectStreamException{
        //return a replacement object for serilastion
        return new ReplacmentObject();
     }

     // readResolve
     private Object readResolve() throws ObjectStreamException{
         //return a replacement object for deSerilastion
         return someExistingObject;
     }


    private String encrypt(String data) {
        // Simple encryption logic (for demonstration purposes)
        return new StringBuilder(data).reverse().toString();
    }

    private String decrypt(String data) {
        // Simple decryption logic (for demonstration purposes)
        return new StringBuilder(data).reverse().toString();
    }

    @Override
    public String toString() {
        return "SecureData{sensitiveData='" + sensitiveData + "'}";
    }

    public static void main(String[] args) {
        SecureData secureData = new SecureData("MySecretData");

        // Serialize the object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("secure_data.ser"))) {
            out.writeObject(secureData);
            System.out.println("SecureData object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("secure_data.ser"))) {
            SecureData deserializedData = (SecureData) in.readObject();
            System.out.println("Deserialized SecureData: " + deserializedData);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
