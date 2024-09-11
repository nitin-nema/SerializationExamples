import java.io.*;

class SessionData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sessionId;
    private transient String token; // Transient field

    public SessionData(String sessionId, String token) {
        this.sessionId = sessionId;
        this.token = token;
    }

    @Override
    public String toString() {
        return "SessionData{sessionId='" + sessionId + "', token='" + token + "'}";
    }

    public static void main(String[] args) {
        SessionData sessionData = new SessionData("sess123", "tokenXYZ");

        // Serialize the object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("session_data.ser"))) {
            out.writeObject(sessionData);
            System.out.println("SessionData object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("session_data.ser"))) {
            SessionData deserializedSessionData = (SessionData) in.readObject();
            System.out.println("Deserialized SessionData: " + deserializedSessionData);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
//            return null;
        }
    }
}
