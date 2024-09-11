import java.io.*;

public class Product implements Serializable {
    private static final long serialVersionUID = 2L; // Version control
    private String productName;
    private double price;

    public Product(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{name='" + productName + "', price=" + price + "}";
    }

    public static void main(String[] args) {
        Product product = new Product("Laptop", 1200.00);

        // Serialize the object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("product.ser"))) {
            out.writeObject(product);
            System.out.println("Product object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("product.ser"))) {
            Product deserializedProduct = (Product) in.readObject();
            System.out.println("Deserialized Product: " + deserializedProduct);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
