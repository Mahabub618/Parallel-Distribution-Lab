import java.rmi.*;
import java.io.*;
public class RMIclient {
    public static void main(String[] args) {
        try {
            
            RMIinterface hello = (RMIinterface) Naming.lookup("//localhost/RMI");
            System.out.println(hello.say());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
