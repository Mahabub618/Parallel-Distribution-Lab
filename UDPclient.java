import java.net.*;
import java.io.*;
public class UDPclient{
	public static void main(String[] args) throws Exception {
		DatagramSocket ds = new DatagramSocket();
		System.out.println("Client Started....");
		String msg = "Hey!! I'm Client";
		byte b[] = msg.getBytes();
		
		InetAddress ia = InetAddress.getByName("localhost");
		DatagramPacket dp = new DatagramPacket(b, b.length,ia,9999);
		ds.send(dp);
		
		System.out.println("Packets Send....");
		
		// For one way communication above code is enough
		
		byte b1[] = new byte[1024];
		DatagramPacket dp1 = new DatagramPacket(b1, b1.length);
		ds.receive(dp1);
		String str = new String(dp1.getData());
		System.out.println("Received:"+str);
		ds.close();
	}
}