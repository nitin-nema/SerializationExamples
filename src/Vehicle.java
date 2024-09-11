import java.io.*;

class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Vehicle{brand='" + brand + "'}";
    }
}

class Car extends Vehicle {
    private static final long serialVersionUID = 1L;
    private String model;

    public Car(String brand, String model) {
        super(brand);
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{model='" + model + "', " + super.toString() + "}";
    }

    public static void main(String[] args) {
        Car car = new Car("Toyota", "Corolla");

        // Serialize the object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("car.ser"))) {
            out.writeObject(car);
            System.out.println("Car object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("car.ser"))) {
            Car deserializedCar = (Car) in.readObject();
            System.out.println("Deserialized Car: " + deserializedCar);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
