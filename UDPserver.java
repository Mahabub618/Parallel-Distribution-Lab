import java.net.*;
import java.io.*;
public class UDPserver
{
	public static void main(String[] args) throws Exception {
		DatagramSocket ds = new DatagramSocket(9999);
		System.out.println("Server Started....."); 
		
		byte[] b = new byte[1024];
		DatagramPacket dp = new DatagramPacket(b, b.length);
		ds.receive(dp);  
		String str = new String(dp.getData());
		System.out.println("Received:"+str);
		
		// For one way communication above code is enough
		String msg = "Hey!! I'm Server";
		byte b1[] = msg.getBytes();
		InetAddress ia = InetAddress.getLocalHost();
		DatagramPacket dp1 = new DatagramPacket(b1, b1.length, ia, dp.getPort());
		ds.send(dp1);
		ds.close();
		
	}
}