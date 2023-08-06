import java.io.*;
import java.rmi.*;
public class RMIserver{
    public static void main(String[] args) {
        try { 
            RMI obj = new RMI("Hello I'm from server!");
            Naming.rebind("RMI", obj);
            System.out.println("Server is ready....");   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
