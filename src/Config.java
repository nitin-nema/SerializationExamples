import java.io.*;

class Config implements Serializable {
    private static final long serialVersionUID = 1L;
    private String databaseUrl;
    private int maxConnections;
    private transient String secretKey; // Sensitive data

    public Config(String databaseUrl, int maxConnections, String secretKey) {
        this.databaseUrl = databaseUrl;
        this.maxConnections = maxConnections;
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "Config{databaseUrl='" + databaseUrl + "', maxConnections=" + maxConnections + ", secretKey='" + secretKey + "'}";
    }

    public static void main(String[] args) {
        Config config = new Config("jdbc:mysql://localhost:3306/mydb", 10, "mySecretKey");

        // Serialize the object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("config.ser"))) {
            out.writeObject(config);
            System.out.println("Config object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("config.ser"))) {
            Config deserializedConfig = (Config) in.readObject();
            System.out.println("Deserialized Config: " + deserializedConfig);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
