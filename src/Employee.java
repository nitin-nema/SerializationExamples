import java.io.*;

public class Employee implements Serializable {
    private static final long serialVersionUID = 2L;  //long literal
    private String name;
    private int employeeId;

    public Employee(String name, int employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', employeeId=" + employeeId + "}";
    }

    //helper medthod to display byte array in hexademiacal format
    public static  void displayBytes(byte[] byteData){
        StringBuilder sb = new StringBuilder();
        for(byte b: byteData){
            sb.append(String.format("%02X ", b));
        }
        System.out.println(sb.toString());
    }


    public static void main(String[] args) {
        Employee emp = new Employee("Aplha", 6464);

        // Serialize the object
        try (ObjectOutputStream serliazedDataOutput = new ObjectOutputStream(new FileOutputStream("employee.ser"))) {
            serliazedDataOutput.writeObject(emp);
            System.out.println("Employee object serialized successfully.");
            System.out.println("serliazedDataOutput --" + serliazedDataOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //read and dispaly serilised byte
        try(FileInputStream fileInputStream = new FileInputStream("employee.ser")){
            byte[] byteData = fileInputStream.readAllBytes();
            System.out.println("Serialised byte data :");
            displayBytes(byteData);
        }catch (IOException e){
            e.printStackTrace();
        }


        // Deserialize the object
        try (ObjectInputStream serliazedDataInput = new ObjectInputStream(new FileInputStream("employee.ser"))) {
            Employee deserializedEmp = (Employee) serliazedDataInput.readObject();
            System.out.println("Deserialized Employee: " + deserializedEmp);
            System.out.println("serialVersionUID  " + serialVersionUID);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

// imp key points
//NotSerializableException
//InavlidClassException-- serialVersionUID

// AC AE